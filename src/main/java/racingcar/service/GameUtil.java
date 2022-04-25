package racingcar.service;

import camp.nextstep.edu.missionutils.Console;
import racingcar.model.Car;
import racingcar.model.Cars;

import java.util.ArrayList;
import java.util.List;

public class GameUtil {
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;
    private static Cars cars;

    public static void inputCarName() {
        while(true) {
            System.out.println("경주 할 자동차 이름(이름은 쉼표(,) 기준으로 구분");
            String[] carsNames = Console.readLine().split(",");
            if(validateNameLength(carsNames)) {
                createCars(carsNames);
                break;
            }
        }
    }

    private static boolean validateNameLength(String[] carsNames) {
        boolean isValid = true;
        for(String carName: carsNames) {
            isValid = compareCarNameLength(carName, isValid);
        }
        return isValid;
    }

    private static boolean compareCarNameLength(String carName, boolean isValid) {
        try {
            compareCarNameLength(carName);
        } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
            isValid = false;
        }
        return isValid;
    }

    private static void compareCarNameLength(String carName) {
        if(carName.length() < MIN_NAME_LENGTH || carName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름은 1자 이상 5자 이하만 가능합니다.");
        }
    }

    public static void createCars(String[] carsNames) {
        List<Car> carArr = new ArrayList<>();
        for(String carName: carsNames) {
            carArr.add(new Car(carName));
        }
        cars = new Cars(carArr);
    }

    public static void inputGameCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        int count = Integer.parseInt(Console.readLine());
        runAndPrint(count);
    }

    private static void runAndPrint(int count) {
        System.out.println("\n실행 결과");
        for(int i=0; i<count; i++) {
            cars.forwardCars();
            cars.printDistance();
        }
    }

    public static void printResult() {
        System.out.print("최종 우승자: ");
        cars.printWinner();
    }
}
