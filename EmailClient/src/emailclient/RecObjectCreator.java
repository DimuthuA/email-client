package emailclient;

class RecObjectCreator {
    private String recipientType;
    private String recipientDetails;

    public Recipient createObject (String type, String details, int lineNumber) {
        // this method checks the type of the recipient, accordingly separates the details and returns
        // a recipient object

        if (type.equalsIgnoreCase("Official")) {
            String[] splitDetails = details.split(",", 3);

            if (splitDetails.length == 3) {
                String name = splitDetails[0].trim();
                String emailAddress = splitDetails[1].trim();
                String designation = splitDetails[2].trim();

                return new RecipientOfficial(name, emailAddress, designation);
            }
        }
        else if (type.equalsIgnoreCase("Office_friend")) {
            String[] splitDetails = details.split(",", 4);

            if (splitDetails.length == 4) {
                String name = splitDetails[0].trim();
                String emailAddress = splitDetails[1].trim();
                String designation = splitDetails[2].trim();
                String birthday = splitDetails[3].trim();

                return new RecipientOfficeFriend(name, emailAddress, designation, birthday);
            }
        } else if (type.equalsIgnoreCase("Personal")) {
            String [] splitDetails = details.split(",", 4);

            if (splitDetails.length == 4) {
                String name = splitDetails[0].trim();
                String nickname = splitDetails[1].trim();
                String emailAddress = splitDetails[2].trim();
                String birthday = splitDetails[3].trim();

                return new RecipientPersonal(name, nickname, emailAddress, birthday);
            }

        } else {
            System.out.println("\nWARNING! : Incomplete or incorrect entry detected at line "+ lineNumber
                                + " in the recipient file.");
            return null;
        }
        return null;
    }
}
