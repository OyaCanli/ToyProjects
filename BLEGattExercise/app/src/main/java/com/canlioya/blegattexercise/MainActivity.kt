package com.canlioya.blegattexercise


import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.ParcelUuid
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.canlioya.blegattexercise.databinding.ActivityMainBinding
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

const val REQUEST_FINE_LOCATION = 761

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val bleScanner by lazy {
        bluetoothAdapter.bluetoothLeScanner
    }

    /*SCAN_MODE_LOW_LATENCY is recommended if the app will
    only be scanning for a brief period of time, typically to
    find a very specific type of device*/
    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    private val ENABLE_BLUETOOTH_REQUEST_CODE = 492

    // Stops scanning after 10 seconds.
    private val SCAN_PERIOD: Long = 10000

    val HEART_RATE_SERVICE_UUID = convertFromInteger(0x180D)
    val HEART_RATE_MEASUREMENT_CHAR_UUID = convertFromInteger(0x2A37)
    val HEART_RATE_CONTROL_POINT_CHAR_UUID = convertFromInteger(0x2A39)
    val CLIENT_CHARACTERISTIC_CONFIG_UUID = convertFromInteger(0x2902)

    fun convertFromInteger(i: Int): UUID {
        val MSB = 0x0000000000001000L
        val LSB = -0x7fffff7fa064cb05L
        val value = (i and -0x1).toLong()
        return UUID(MSB or (value shl 32), LSB)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startScanBtn.setOnClickListener {
            showScanning()
            checkPermissionsAndStartScan()
        }
    }

    private fun showScanning() {
        binding.startScanBtn.text = "Scanning"
        binding.loadingIndicator.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        if (!bluetoothAdapter.isEnabled) {
            promptEnableBluetooth()
        }
    }

    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST_CODE)
        }
    }

    @AfterPermissionGranted(REQUEST_FINE_LOCATION)
    private fun checkPermissionsAndStartScan() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || EasyPermissions.hasPermissions(this, ACCESS_FINE_LOCATION)) {
            // Doesn't need permission or already have permission
            Log.d("MainActivity", "Permission granted. Starting scanning")
            startScan()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                host = this,
                rationale = getString(R.string.permission_location_rationale),
                requestCode = REQUEST_FINE_LOCATION,
                ACCESS_FINE_LOCATION
            )
        }
    }

    private fun startScan() {
        val heartRateFilter = ScanFilter.Builder().setServiceUuid(
            ParcelUuid(HEART_RATE_SERVICE_UUID)
        ).build()
        lifecycleScope.launchWhenResumed {
            bleScanner.startScan(listOf(heartRateFilter), scanSettings, bleScanCallback)
            delay(SCAN_PERIOD)
            bleScanner.stopScan(bleScanCallback)
            stopScanning()
        }
    }

    private fun stopScanning() {
        binding.loadingIndicator.visibility = View.INVISIBLE
        binding.startScanBtn.text = "Start Scan"
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            ENABLE_BLUETOOTH_REQUEST_CODE -> {
                if (resultCode != Activity.RESULT_OK) {
                    promptEnableBluetooth()
                } else {
                    // User chose not to enable Bluetooth.
                    Toast.makeText(
                        this,
                        "Bluetooth is not enabled. Cannot work without bluetooth.",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    // Device scan callback.
    private val bleScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            result?.run {
                Log.d("MainActivity", "Result: $this")
                Log.d("MainActivity", "Device: ${this.device.uuids}")
                binding.scanResults.text = "Device: ${this.device.name}"
                this.device.connectGatt(applicationContext, true, gattCallback)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e("MainActivity", "Scan failed with error code: $errorCode")
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            Log.d("MainActivity", "Connection state changed. newState: $newState")
            if (newState == BluetoothGatt.STATE_CONNECTED) {
                Log.d("MainActivity", "State is connected. Start discovering services")
                gatt?.discoverServices()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            //This first part is for experimenting
            gatt?.services?.forEach { service ->
                Log.d("MainActivity", "service: $service")
                service.characteristics.forEach { character ->
                    Log.d("MainActivity", "characteristic: $character")
                }
            }

            //Get the characteristic that you're interested
            val heartRateCharacteristic = gatt?.getService(HEART_RATE_SERVICE_UUID)?.getCharacteristic(HEART_RATE_MEASUREMENT_CHAR_UUID)
            val descriptor = heartRateCharacteristic?.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_UUID)
            descriptor?.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            gatt?.writeDescriptor(descriptor)
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            Log.d("MainActivity", "characteristic read: ${characteristic?.value}")
            binding.charValues.text = "value: ${characteristic?.value}"
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            Log.d("MainActivity", "characteristic changed: $characteristic")
        }

        override fun onDescriptorRead(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            Log.d("MainActivity", "descriptor read: $descriptor")
        }

        override fun onDescriptorWrite(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            val heartControlPointCharacteristic = gatt?.getService(HEART_RATE_SERVICE_UUID)?.getCharacteristic(HEART_RATE_CONTROL_POINT_CHAR_UUID)
            heartControlPointCharacteristic?.value = byteArrayOf(1, 1)
            gatt?.writeCharacteristic(heartControlPointCharacteristic)
        }
    }

}