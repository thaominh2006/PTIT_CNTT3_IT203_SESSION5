import java.util.Scanner;
import java.util.regex.Pattern;

public class miniproject {

    static String[] mssvList = new String[100];

    static String[] initialMssv = {
            "B2101234",
            "B2102345",
            "B2103456",
            "B2104567"
    };

    static int count = initialMssv.length;

    static final String MSSV_REGEX = "^B\\d{7}$";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < initialMssv.length; i++) {
            mssvList[i] = initialMssv[i];
        }

        int choice;
        do {
            System.out.println("\n========== MENU QUẢN LÝ MSSV ==========");
            System.out.println("1. Hiển thị danh sách MSSV");
            System.out.println("2. Thêm mới MSSV");
            System.out.println("3. Cập nhật MSSV theo vị trí");
            System.out.println("4. Xóa MSSV");
            System.out.println("5. Tìm kiếm MSSV");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            choice = sc.nextInt();
            sc.nextLine(); // xử lý trôi lệnh

            switch (choice) {
                case 1:
                    hienThi();
                    break;
                case 2:
                    themMoi(sc);
                    break;
                case 3:
                    capNhat(sc);
                    break;
                case 4:
                    xoa(sc);
                    break;
                case 5:
                    timKiem(sc);
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);

        sc.close();
    }

    static void hienThi() {
        if (count == 0) {
            System.out.println("Danh sách MSSV rỗng!");
            return;
        }

        System.out.println("\nDanh sách MSSV:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + mssvList[i]);
        }
    }

    static void themMoi(Scanner sc) {
        if (count == 100) {
            System.out.println("Mảng đã đầy, không thể thêm!");
            return;
        }

        String mssv;
        while (true) {
            System.out.print("Nhập MSSV mới: ");
            mssv = sc.nextLine();

            if (Pattern.matches(MSSV_REGEX, mssv)) {
                break;
            } else {
                System.out.println("MSSV không hợp lệ! (Ví dụ: B2101234)");
            }
        }

        mssvList[count++] = mssv;
        System.out.println("Thêm MSSV thành công!");
    }

    static void capNhat(Scanner sc) {
        if (count == 0) {
            System.out.println("Danh sách rỗng, không thể cập nhật!");
            return;
        }

        System.out.print("Nhập vị trí cần sửa (1 - " + count + "): ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > count) {
            System.out.println("Vị trí không hợp lệ!");
            return;
        }

        String newMssv;
        while (true) {
            System.out.print("Nhập MSSV mới: ");
            newMssv = sc.nextLine();

            if (Pattern.matches(MSSV_REGEX, newMssv)) {
                break;
            } else {
                System.out.println("MSSV không hợp lệ!");
            }
        }

        mssvList[index - 1] = newMssv;
        System.out.println("Cập nhật thành công!");
    }

    static void xoa(Scanner sc) {
        if (count == 0) {
            System.out.println("Danh sách rỗng, không thể xóa!");
            return;
        }

        System.out.print("Nhập MSSV cần xóa: ");
        String target = sc.nextLine();

        int position = -1;
        for (int i = 0; i < count; i++) {
            if (mssvList[i].equals(target)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            System.out.println("Không tìm thấy MSSV!");
            return;
        }
        
        for (int i = position; i < count - 1; i++) {
            mssvList[i] = mssvList[i + 1];
        }
        mssvList[count - 1] = null;
        count--;

        System.out.println("Xóa MSSV thành công!");
    }

    static void timKiem(Scanner sc) {
        if (count == 0) {
            System.out.println("Danh sách rỗng!");
            return;
        }

        System.out.print("Nhập chuỗi cần tìm: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        System.out.println("\nKết quả tìm kiếm:");
        for (int i = 0; i < count; i++) {
            if (mssvList[i].toLowerCase().contains(keyword)) {
                System.out.println((i + 1) + ". " + mssvList[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có MSSV phù hợp!");
        }
    }
}
