package managers;

import exceptions.BuildObjectException;
import managerscollection.ModeManager;
import objects.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StudyGroupNonUserManager implements ModeManager<StudyGroup> {
    Scanner scanner;
    public StudyGroupNonUserManager(Scanner scanner){
        this.scanner = scanner;
    }
    @Override
    public StudyGroup buildObject() throws BuildObjectException {
        try{
            String name = scanner.nextLine();
            Coordinates coord = new Coordinates(scanner.nextFloat(), scanner.nextFloat());
            int studentsCount = scanner.nextInt();
            long transferredStudents = scanner.nextLong();
            int averageMark = scanner.nextInt();
            String formOfEducation = scanner.nextLine();
            String adminName = scanner.nextLine();
            String adminNationality = scanner.nextLine();
            String birthday = scanner.nextLine();
            String eye = scanner.nextLine();
            String hair = scanner.nextLine();
            Date adminBirthday;
            if (birthday == ""){
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                adminBirthday = df.parse(scanner.nextLine());
            } else{
                adminBirthday = null;
            }
            EyeColor eyeColor;
            if (eye == ""){
                eyeColor = EyeColor.valueOf(eye);
            } else{
                eyeColor = null;
            }
            HairColor hairColor;
            if (hair == ""){
                hairColor = HairColor.valueOf(hair);
            } else{
                hairColor = null;
            }
            Person admin = new Person(name, eyeColor, hairColor, Country.valueOf(adminNationality), adminBirthday);
            return new StudyGroup(name, coord, studentsCount, transferredStudents, averageMark, FormOfEducation.valueOf(formOfEducation), admin);
        } catch (ParseException e) {
            throw new BuildObjectException("The input data is incorrect, the object cannot be created");
        }
    }
}
