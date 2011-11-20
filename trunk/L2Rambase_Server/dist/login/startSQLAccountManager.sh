#!/bin/sh
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*:l2Rambase_login.jar l2.Rambase.tools.accountmanager.SQLAccountManager
