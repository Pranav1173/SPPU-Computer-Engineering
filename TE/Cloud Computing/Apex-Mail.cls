Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
mail.setToAddresses(new String[] {'pranavn_c21042@students.isquareit.edu.in'});
mail.setSubject('Test Email from Salesforce Apex subject');
mail.setPlainTextBody('Hello, this is a test email sent from Salesforce using Apex.');
Messaging.sendEmail(new Messaging.SingleEmailMessage[] { mail });
