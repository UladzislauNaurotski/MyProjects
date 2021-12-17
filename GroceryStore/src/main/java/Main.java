import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CreatingAStore store = new CreatingAStore();
        store.init();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] team = input.split("\\s");
            try {
                if (team[0].contains("Добавить_магазин") && team.length == 2) {
                    store.addShop(team[1]);
                    System.out.println("Магазин успешно создан");
                } else {
                    if (team[0].contains("Добавить_продукт") && team.length == 3) {
                        store.addProduct(team[1], Integer.valueOf(team[2]));
                        System.out.println("Продукт успешно создан");
                    } else {
                        if (team[0].contains("Выставить_товар") && team.length == 3) {
                            store.addProductToShop(team[1], team[2]);
                            System.out.println("Товар выставлен");
                        } else {
                            if (team[0].contains("Статистика_товаров") && team.length == 1) {
                                store.getStat();
                            } else {
                                System.out.println("Не правильный формат команды");
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Что то пошло не так повторите свою попытку)");
            }
        }
    }
}