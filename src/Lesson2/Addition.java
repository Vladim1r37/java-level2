package Lesson2;


public class Addition {

    public static void main(String[] args) {
        String[][] nums1 = {
                {"12","23","-5","15"},
                {"-26","15","48","-132"},
                {"189","-56","38","54"},
                {"2","67","-6","91"}
        };

        String[][] nums2 = {
                {"12","23","-5"},
                {"-26","15","48"},
                {"189","-56","38"},
                {"2","67","-6"}
        };

        String[][] nums3 = {
                {"12","23","-5","15"},
                {"-26","15","some num","-132"},
                {"189","-56","38","54"},
                {"2","67","-6","91"}
        };


        try {
            System.out.println("Массив 1");
            System.out.println("Сумма чисел массива: " + sumOfNumsInArray(nums1));
        } catch (MyArraySizeException e) {
            System.out.println("Некорректный размер массива");
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Массив 2");
            System.out.println("Сумма чисел массива: " + sumOfNumsInArray(nums2));
        } catch (MyArraySizeException e) {
            System.out.println("Некорректный размер массива");
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Массив 3");
            System.out.println("Сумма чисел массива: " + sumOfNumsInArray(nums3));
        } catch (MyArraySizeException e) {
            System.out.println("Некорректный размер массива");
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }


    }

    private static int sumOfNumsInArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4)
            throw new  MyArraySizeException();
        int sum = 0;
        int rowNum = 0;
        int cellNum = 0;
        for (String[] row : arr) {
            if (row.length != 4)
                throw new MyArraySizeException();
            try {
                for (String num : row) {
                    sum += Integer.parseInt(num);
                    cellNum++;
                }
            }
            catch (NumberFormatException e) {
                throw new MyArrayDataException("Некорректный элемент в ряду " + rowNum + " в ячейке " + cellNum);
            }
            rowNum++;
            cellNum = 0;
        }
        return sum;
    }
}
