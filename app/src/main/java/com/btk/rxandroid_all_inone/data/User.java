package com.btk.rxandroid_all_inone.data;

public class User {

    private String name;
    private int id;
    private String address;

    private User(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.id = userBuilder.id;
        this.address = userBuilder.address;
    }

    static class UserBuilder {

        private String name;
        private int id;
        private String address;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
