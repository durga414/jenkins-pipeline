#!/bin/groovy

import com.devops.batch.git.*
import com.devops.batch.build.*
import  com.devops.batch.tools.*
import  com.devops.batch.properties.*

def call(body)
{
   def config = [:]
   body.resolveStrategy = Closure.DELEGATE_FIRST
   body.delegate = config
   body()
   def jp = new jenkins()
   def g = new git()
   def j = new jdk()
   def m = new maven()
   def mb = new mavenBuild()
   stage('Setup Jenkins Properties'){
     try {
            wrap([$class: 'AnsiColorBuildWrapper']) {
            jp.setProperties()
            }
        }
        catch (error)
        {
          wrap([$class: 'AnsiColorBuildWrapper']) {
              echo "Propeties Initializing Failed..."
              throw error
          }
        }
   }
   stage('Preparing Devops setup'){
      try {
            wrap([$class: 'AnsiColorBuildWrapper']) {
            def VERSION = "JDK1.8.101"
            j.setJavaHome("${VERSION}")

          }
        }
        catch (error)
        {
          wrap([$class: 'AnsiColorBuildWrapper']) {
              echo "JAVA Initializing Failed..."
              throw error
          }
        }
        try {
        wrap([$class: 'AnsiColorBuildWrapper']) {
            def MVN_VERSION = "MAVEN-3.5"
            m.setMavenHome("${MVN_VERSION}")
          }
        }
        catch (error){
          wrap([$class: 'AnsiColorBuildWrapper']) {
              throw error
          }
        }
    }
    stage('Checkout Codebase using git'){
      try {
      g.gitCheckout()
      echo "\u001B[41m[SUCCESS] Source Code successfully downloaded"
      }
      catch (Exception error)
      {
          wrap([$class: 'AnsiColorBuildWrapper']) {
          echo "\u001B[41m[ERROR] ${error}"
          throw error
          }
      }
    }
    stage('Building code Using Maven'){
        try{
          echo "${MAVEN_VERSION}"
          def GOALS = "clean compile install"
          mb.createBuild("${WORKSPACE}" ,"${MAVEN_VERSION}", "${GOALS}")
          }
          catch (Exception error){
          wrap([$class: 'AnsiColorBuildWrapper']) {
            print "\u001B[41m[INFO]: ${error}"
            throw error
          }
      }
    }
}