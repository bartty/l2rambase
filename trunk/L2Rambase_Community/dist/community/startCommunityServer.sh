#!/bin/sh
java -Xms128m -Xmx128m -cp ./../libs/*:l2Rambase_community.jar l2.Rambase.communityserver.L2CommunityServer > log/stdout.log 2>&1