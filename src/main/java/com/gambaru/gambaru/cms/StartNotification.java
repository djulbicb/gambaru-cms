package com.gambaru.gambaru.cms;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class StartNotification implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        JOptionPane.showMessageDialog(null, "Welcome to My Application!");
    }
}
