package edu.ucmo.devet.controller;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ControllersModule extends AbstractModule {
    
    @Override
    protected void configure() {
        Multibinder<Controller> controllerMultiBinder = Multibinder.newSetBinder(binder(), Controller.class);
        
        controllerMultiBinder.addBinding().to(ReposController.class);
    }
}
