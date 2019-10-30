package com.mainor.learn;

public class PlayingWithEnum {
    public enum Type {
        SALE,
        AUTH,
        TOKEN,
        CAPTURE,
        VOID,
        REFUND
    }

    public FakeTransaction letsChangeTheType(boolean isVoid, Type type) {
        return new FakeTransaction(
                1,
                "APPROVED",
                isVoid ? Type.VOID : Type.REFUND
        );
    }

    public class FakeTransaction {
        private int id;
        private String status;
        private Type type;

        public FakeTransaction(int id, String status, Type type) {
            this.id = id;
            this.status = status;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "{" + getId() + ", " + getStatus() + ", " + getType() + "}";
        }
    }
}
