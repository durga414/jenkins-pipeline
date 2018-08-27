#!/bin/groovy
package com.devops.batch.tools

def setJavaHome(VERSION)
{
   try {
     wrap([$class: 'AnsiColorBuildWrapper']) {
       env.JAVA_HOME="${tool "${VERSION}"}"
       env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
       sh 'java -version'

     }
   }
   catch (error) {
     wrap([$class: 'AnsiColorBuildWrapper']) {
         print "\u001B[41m[ERROR]: failed to set JAVA_HOME to.."
        throw error
     }
   }
}