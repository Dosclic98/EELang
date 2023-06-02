/*
 * Copyright 2020-2022 Foreseeti AB <https://foreseeti.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mal_lang.eelang.test;

import core.Attacker;
import core.Asset;
import core.AttackStep;
import core.Defense;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

public class TestEELang {
  @Test
  public void testScenario1() {

    //Assets
    var network = new Network();
    var channel = new Channel(); // unsecure channel
    var dataflow = new Dataflow();


    //Asset connections and attacker creation

    network.addChannel(channel);
    channel.addDataflow(dataflow);

    var attacker = new Attacker();
    attacker.addAttackPoint(network.access);
    attacker.attack();

    //Assertions

    channel.adversaryInTheMiddle.assertCompromisedWithEffort();
    dataflow.read.assertCompromisedInstantaneously();
    dataflow.write.assertCompromisedInstantaneously();
    dataflow.delete.assertCompromisedInstantaneously();

  }

  @Test
  public void testScenario2() {

    //Assets
    var network = new Network();
    var channel = new Channel(true); // secure channel
    var dataflow = new Dataflow();

    var attacker = new Attacker();
    attacker.addAttackPoint(network.access);
    attacker.attack();

    //Assertions

    channel.adversaryInTheMiddle.assertUncompromised();
    dataflow.read.assertUncompromised();
    dataflow.write.assertUncompromised();
    dataflow.delete.assertUncompromised();

  }

  @Test
  public void testScenario3() {

    //Assets

    var ied = new IED();
    var network = new Network();
    var channel = new Channel(true);
    var mmsServer = new MMSServer();
    var powerSystem = new PowerSystem();

    //Asset connections and attacker creation

    ied.addNetwork(network);
    network.addChannel(channel);
    channel.addMmsServer(mmsServer);
    mmsServer.addPowerSystem(powerSystem);

    var attacker = new Attacker();
    attacker.addAttackPoint(ied.unsecuredCredentials);
    attacker.attack();

    //Assertions
    ied.modifyAuthenticationProcess.assertCompromisedInstantaneously();
    network.access.assertCompromisedInstantaneously();
    channel.adversaryInTheMiddleOverTLS.assertCompromisedInstantaneously();
    mmsServer.unauthorizedCommandMessage.assertCompromisedInstantaneously();
    powerSystem.unstablePowerSystem.assertCompromisedInstantaneously();
  }

  @Test
  public void testScenario4() {

    //Assets
    var ied = new IED();
    var powerSystem = new PowerSystem();

    var attacker = new Attacker();
    attacker.addAttackPoint(ied.internetAccessibleDevice);
    attacker.attack();

    //Asset connections and attacker creation

    ied.addPowerSystem(powerSystem);

    //Assertions
    ied.modifyControllerTasking.assertCompromisedInstantaneously();
    ied.installMalware.assertCompromisedInstantaneously();
    powerSystem.unstablePowerSystem.assertUncompromised();

  }


  @AfterEach
  public void deleteModel() {
    Asset.allAssets.clear();
    AttackStep.allAttackSteps.clear();
    Defense.allDefenses.clear();
  }
}