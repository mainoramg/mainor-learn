package com.mainoramg.buchalka.section8;

import java.util.ArrayList;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public boolean addNewContact(Contact contact) {
        int position = findContact(contact);
        if (position == -1) {
            myContacts.add(contact);
            return true;
        }

        return false;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int position = findContact(oldContact);
        if (position >= 0) {
            myContacts.set(position, newContact);
            return true;
        }

        return false;
    }

    public boolean removeContact(Contact contact) {
        int position = findContact(contact);
        if (position >= 0) {
            myContacts.remove(position);
            return true;
        }

        return false;
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return myContacts.get(position);
        }

        return null;
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            System.out.print((i + 1) + ". " + contact.getName() + " -> " + contact.getPhoneNumber());
        }
    }

    private int findContact(Contact contact) {
        return findContact(contact.getName());
    }

    private int findContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact c = myContacts.get(i);
            if (name.equals(c.getName())) {
                return i;
            }
        }

        return -1;
    }

    public MobilePhone(String phoneNumber) {
        this.myNumber = phoneNumber;
        myContacts = new ArrayList<>();
    }
}
