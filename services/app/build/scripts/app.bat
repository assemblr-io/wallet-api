@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  app startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and APP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\app.jar;%APP_HOME%\lib\web.jar;%APP_HOME%\lib\database.jar;%APP_HOME%\lib\domain.jar;%APP_HOME%\lib\kotlinx-datetime-jvm-0.4.0.jar;%APP_HOME%\lib\ktor-server-content-negotiation-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-server-status-pages-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-server-jetty-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-server-servlet-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-server-host-common-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-server-core-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-serialization-kotlinx-json-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-serialization-kotlinx-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-serialization-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-events-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-websockets-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-http-cio-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-http-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-network-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-utils-jvm-2.3.1.jar;%APP_HOME%\lib\ktor-io-jvm-2.3.1.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.8.10.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.8.10.jar;%APP_HOME%\lib\kotlin-reflect-1.8.10.jar;%APP_HOME%\lib\kotlinx-serialization-core-jvm-1.5.1.jar;%APP_HOME%\lib\kotlinx-serialization-json-jvm-1.5.1.jar;%APP_HOME%\lib\kotlin-stdlib-1.8.21.jar;%APP_HOME%\lib\postgresql-42.5.1.jar;%APP_HOME%\lib\logback-classic-1.4.8.jar;%APP_HOME%\lib\kotlinx-coroutines-jdk8-1.6.4.jar;%APP_HOME%\lib\kotlinx-coroutines-core-jvm-1.6.4.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.8.21.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\slf4j-api-2.0.7.jar;%APP_HOME%\lib\checker-qual-3.5.0.jar;%APP_HOME%\lib\config-1.4.2.jar;%APP_HOME%\lib\jansi-2.4.0.jar;%APP_HOME%\lib\h2-2.1.214.jar;%APP_HOME%\lib\logback-core-1.4.8.jar;%APP_HOME%\lib\jetty-alpn-java-server-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-alpn-openjdk8-server-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-alpn-server-9.4.51.v20230217.jar;%APP_HOME%\lib\http2-server-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-server-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-servlets-9.4.51.v20230217.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\http2-common-9.4.51.v20230217.jar;%APP_HOME%\lib\http2-hpack-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-http-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-io-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-continuation-9.4.51.v20230217.jar;%APP_HOME%\lib\jetty-util-9.4.51.v20230217.jar


@rem Execute app
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %APP_OPTS%  -classpath "%CLASSPATH%" vgw.wallets.lnd.ApplicationKt %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable APP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%APP_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
