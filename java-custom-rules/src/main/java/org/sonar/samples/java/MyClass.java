/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java;

import java.util.concurrent.CompletableFuture;

import javax.ws.rs.client.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * -------------------------------------------------------------------------
 * Copyright 2016 (C) by Thales Alenia Space France - all rights reserved
 * -------------------------------------------------------------------------
 */

public class MyClass
{
  
  private static Logger logger = LoggerFactory.getLogger(MyClass.class);
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    CompletableFuture.supplyAsync(() -> 
    { 
      String url = "http://www.google.fr";
      return ClientBuilder.newClient().target(url).request().async().get(String.class);
    })
    .thenAccept(s -> 
    {
      logger.info(s.toString());
    });
  }
}
