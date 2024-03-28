package managers;

import exceptions.BuildObjectException;
import objects.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StudyGroupCLIManager implements managerscollection.ModeManager<objects.StudyGroup> {
    @Override
    public StudyGroup buildObject() throws BuildObjectException {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        while (name.length() == 0) {
            System.out.println("Enter name(not null!)(type: String) : ");
            name = scanner.nextLine();
        }
        float coordX;
        while (true) {
            System.out.println("Enter coordinates_x(not null!) (type: float) : ");
            String x = scanner.nextLine();
            try {
                coordX = Float.parseFloat(x);
                break;
            } catch (NumberFormatException e) {
                System.out.println("It's not float");
            }
        }
        float coordY;
        while (true) {
            System.out.println("Enter coordinates_y(not null!) (type: float) : ");
            String y = scanner.nextLine();
            try {
                coordY = Float.parseFloat(y);
                break;
            } catch (NumberFormatException e) {
                System.out.println("It's not float");
            }
        }
        Coordinates coord = new Coordinates(coordX, coordY);
        int studentsCount;
        while (true) {
            System.out.println("Enter students count(not null!) (type: int > 0) : ");
            String y = scanner.nextLine();
            try {
                studentsCount = Integer.parseInt(y);
                break;
            } catch (NumberFormatException e) {
                System.out.println("It's not int");
            }
        }
        long transferredStudents;
        while (true) {
            System.out.println("Enter transferred students (not null!) (type: long > 0) : ");
            String y = scanner.nextLine();
            try {
                transferredStudents = Long.parseLong(y);
                break;
            } catch (NumberFormatException e) {
                System.out.println("It's not long");
            }
        }
        int averageMark;
        while (true) {
            System.out.println("Enter average mark(not null!) (type: int > 0) : ");
            String y = scanner.nextLine();
            try {
                averageMark = Integer.parseInt(y);
                break;
            } catch (NumberFormatException e) {
                System.out.println("It's not int");
            }
        }


        String formOfEducation = "hgf";
        FormOfEducation education;
        while (!formOfEducation.equals("DISTANCE_EDUCATION") && !formOfEducation.equals("FULL_TIME_EDUCATION") && !formOfEducation.equals("EVENING_CLASSES")) {
            System.out.println("Enter form of education(not null!) (DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES) : ");
            formOfEducation = scanner.nextLine();
        }
        education = FormOfEducation.valueOf(formOfEducation);

        String adminName = "";
        while (adminName.length() == 0) {
            System.out.println("Enter groupAdmin's name(not null!) (type: String) : ");
            adminName = scanner.nextLine();
        }

        String adminNationality = "hgf";
        Country country;
        while (!adminNationality.equals("USA") && !adminNationality.equals("GERMANY") && !adminNationality.equals("SPAIN") && !adminNationality.equals("CHINA") && !adminNationality.equals("JAPAN")) {
            System.out.println("Enter nationality(not null!) (USA, GERMANY, SPAIN, CHINA, JAPAN) : ");
            adminNationality = scanner.nextLine();
        }
        country = Country.valueOf(adminNationality);


        Date adminBirthday = null;
        while (adminBirthday == null) {
            System.out.println("Enter birthday (type: date) : ");
            try {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String date = scanner.nextLine();
                if (date.equals("")){
                    break;
                }
                adminBirthday = df.parse(date);
                System.out.println(adminBirthday.toString());
            } catch (ParseException e) {
                adminBirthday = null;
            }
        }


        String eye = "hgf";
        EyeColor eyeColor;
        while (!eye.equals("") && !eye.equals("BLACK") && !eye.equals("YELLOW") && !eye.equals("ORANGE")) {
            System.out.println("Enter eye color (BLACK, YELLOW, ORANGE) : ");
            eye = scanner.nextLine();
        }
        if (!eye.equals("")) {
            eyeColor = EyeColor.valueOf(eye);
        } else {
            eyeColor = null;
        }


        String hair = "hgf";
        HairColor hairColor;
        while (!hair.equals("") && !hair.equals("RED") && !hair.equals("BLACK") && !hair.equals("ORANGE") && !hair.equals("WHITE")) {
            System.out.println("Enter hair color (RED, BLACK, ORANGE, WHITE) : ");
            hair = scanner.nextLine();
        }
        if (!hair.equals("")) {
            hairColor = HairColor.valueOf(hair);
        } else {
            hairColor = null;
        }

        Person admin = new Person(adminName, eyeColor, hairColor, country, adminBirthday);
        return new StudyGroup(name, coord, studentsCount, transferredStudents, averageMark, education, admin);
    }
}
