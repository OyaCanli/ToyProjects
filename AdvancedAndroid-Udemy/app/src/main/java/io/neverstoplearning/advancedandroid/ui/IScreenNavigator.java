package io.neverstoplearning.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface IScreenNavigator {

    void initWithRouter(Router router, Controller controller);

    boolean pop();

    void clear();

}
