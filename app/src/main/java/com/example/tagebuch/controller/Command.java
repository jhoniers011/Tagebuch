package com.example.tagebuch.controller;

public abstract class   Command {

    public static PilaTamanoFijo PilaRehacer = new PilaTamanoFijo(10);
    public static PilaTamanoFijo PilaDeshacer = new PilaTamanoFijo(10);

    public abstract void  execute();



}
