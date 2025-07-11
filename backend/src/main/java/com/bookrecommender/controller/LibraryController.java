package com.bookrecommender.controller;

import javax.naming.Context;

public class LibraryController {
    public void  getAllLibraries(Context ctx)
    {
        List<library> library = LibrarySQL.getAllLibraries();
        ctx.json(library);
        ctx.status(200);

    }
    
    public void registerLibrary(Context ctx){

    }
    public void getLibrariesByUser(Context ctx){

    }
    public void getLibraryDetail(Context ctx){

    }
}
