name := """APP_PROJECT"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies += guice

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)