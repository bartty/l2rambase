@echo off
title Community Server Console
echo Starting L2Rambase Community Server.
echo.
java -Xms128m -Xmx128m -cp ./../libs/*;l2Rambase_community.jar l2.Rambase.communityserver.L2CommunityServer
pause
