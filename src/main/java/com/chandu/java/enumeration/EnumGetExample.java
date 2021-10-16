package com.chandu.java.enumeration;

public class EnumGetExample {

    public static void main(String[] args) {
        System.out.println(Status.getValue("INITIATED"));
        System.out.println(Status.getValue("COMPLETED"));
        System.out.println(Status.getValue("FAILED"));
        for (Status status : Status.values()) {
            System.out.println(status.name() + " , " + status.value);
        }
    }

    public enum Status {
        INITIATED("INITIATED VALUE"), COMPLETED("COMPLETED VALUE"), FAILED("FAILED VALUE");

        private String value;

        Status(String value) {
            this.value = value;
        }

        public static String getValue(String key) {
            return valueOf(key).getValue();
        }

        public String getValue() {
            return value;
        }
    }
}
