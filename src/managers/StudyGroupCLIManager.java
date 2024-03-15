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
        try {
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
            // исправить неправильный ввод (добавить while)
            System.out.println("Enter form of education (DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES) : ");
            String formOfEducation = scanner.nextLine();
            System.out.println("Enter groupAdmin's name(not null!) (type: String) : ");
            String adminName = "";
            while (name.length() == 0) {
                System.out.println("Enter groupAdmin's name(not null!) (type: String) : ");
                name = scanner.nextLine();
            }
            // исправить неправильный ввод (добавить while)
            System.out.println("Enter nationality(not null!) (USA, GERMANY, SPAIN, CHINA, JAPAN) : ");
            String adminNationality = scanner.nextLine();
            System.out.println("Enter birthday (type: date) : ");
            String birthday = scanner.nextLine();
            System.out.println("Enter eye color (BLACK, YELLOW, ORANGE) : ");
            String eye = scanner.nextLine();
            System.out.println("Enter hair color (RED, BLACK, ORANGE, WHITE) : ");
            String hair = scanner.nextLine();
            Date adminBirthday;
            if (birthday != "") {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                adminBirthday = df.parse(scanner.nextLine());
            } else {
                adminBirthday = null;
            }
            EyeColor eyeColor;
            if (eye != "") {
                eyeColor = EyeColor.valueOf(eye);
            } else {
                eyeColor = null;
            }
            HairColor hairColor;
            if (hair != "") {
                hairColor = HairColor.valueOf(hair);
            } else {
                hairColor = null;
            }
            Person admin = new Person(name, eyeColor, hairColor, Country.valueOf(adminNationality), adminBirthday);
            return new StudyGroup(name, coord, studentsCount, transferredStudents, averageMark, FormOfEducation.valueOf(formOfEducation), admin);
        } catch (ParseException e) {
            throw new BuildObjectException("The input data is incorrect, the object cannot be created");
        }
    }
}
