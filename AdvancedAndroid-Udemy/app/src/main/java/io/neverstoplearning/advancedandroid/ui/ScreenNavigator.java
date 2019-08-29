package io.neverstoplearning.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.di.ActivityScope;

@ActivityScope
public class ScreenNavigator implements IScreenNavigator {

    private Router router;

    @Inject
    public ScreenNavigator() {
    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        //This is for setting the router the first time. It won't be recreated after a configuration change
        if(!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }

    @Override
    public void clear() {
        router = null;
    }
}
