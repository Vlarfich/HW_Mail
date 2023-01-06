package com.solvd.mail.connectionPool;


class Tasks {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void ping(Friend bower) {
            System.out.format("%s:   ping  %s!%n", this.name, bower.getName());
            bower.pong(this);
        }

        public synchronized void pong(Friend bower) {
            System.out.format("%s:   pong  %s!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend A = new Friend("A");
        final Friend B = new Friend("B");
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                A.ping(B);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                B.ping(A);

            }
        }).start();
    }
}