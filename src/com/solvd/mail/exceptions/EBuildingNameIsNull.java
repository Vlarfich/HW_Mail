package com.solvd.mail.exceptions;
//                                          Throwable
//                                              |
//                               Error                   Exception
//                                 |                         |
//                ThreadDead, VM, IO              Runtime         #IO, SQL, BrokenBarrier#
//                            |                      |
//          OutOfMemory, StackOverflow        NullPointer, Arithmetic


public class EBuildingNameIsNull extends Exception{
    public EBuildingNameIsNull(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
