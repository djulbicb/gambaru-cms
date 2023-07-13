package com.gambaru.gambaru.cms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class GambaruCmsApplication {

	public static void main(String[] args) {
		var ctx = new SpringApplicationBuilder(GambaruCmsApplication.class)
				.headless(false).run(args);
	}

}
