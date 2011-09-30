#!/bin/sh
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*:l2rambaselogin.jar l2.rambase.accountmanager.SQLAccountManager
