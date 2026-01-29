import java.util.Scanner;
import java.util.Arrays;
public class BTTH {
    // Scanner dùng chung cho toàn bộ chương trình
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        // Menu lặp lại cho đến khi người dùng chọn 0
        do {
            showMenu();
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    twoSumBasic();
                    break;
                case 2:
                    moveZeroes();
                    break;
                case 3:
                    validPalindrome();
                    break;
                case 4:
                    reverseWords();
                    break;
                case 5:
                    happyNumber();
                    break;
                case 0:
                    System.out.println("Thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 0);
    }

    // ================= MENU =================
    // Hiển thị danh sách chức năng
    static void showMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Two Sum");
        System.out.println("2. Move Zeroes");
        System.out.println("3. Valid Palindrome");
        System.out.println("4. Reverse Words");
        System.out.println("5. Happy Number");
        System.out.println("0. Thoát");
    }

    // ================= FR1: TWO SUM =================
    // Tìm hai chỉ số i, j sao cho arr[i] + arr[j] = target
    static void twoSumBasic() {
        System.out.print("Nhập số phần tử: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }

        System.out.print("Nhập target: ");
        int target = sc.nextInt();

        // Duyệt hai vòng for để kiểm tra mọi cặp phần tử
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    System.out.println("Tìm thấy cặp chỉ số: [" + i + ", " + j + "]");
                    return;
                }
            }
        }

        System.out.println(" Không tìm thấy cặp phù hợp");
    }

    // ================= FR2: MOVE ZEROES =================
    // Dồn tất cả số 0 về cuối mảng, không dùng mảng phụ
    static void moveZeroes() {
        System.out.print("Nhập số phần tử : ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }

        // pos là vị trí đặt phần tử khác 0 tiếp theo
        int pos = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                // Hoán đổi arr[i] với arr[pos]
                int temp = arr[pos];
                arr[pos] = arr[i];
                arr[i] = temp;
                pos++;
            }
        }

        System.out.println("Mảng sau khi dồn 0:");
        System.out.println(Arrays.toString(arr));
    }

    // ================= FR3: VALID PALINDROME =================
    // Kiểm tra chuỗi đối xứng (bỏ ký tự đặc biệt, không phân biệt hoa thường)
    static void validPalindrome() {
        sc.nextLine(); // Xóa ký tự xuống dòng còn sót lại
        System.out.print("Nhập chuỗi: ");
        String input = sc.nextLine();

        // Loại bỏ ký tự không phải chữ hoặc số và chuyển về chữ thường
        String clean = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = clean.length() - 1;
        boolean ok = true;

        // Kỹ thuật hai con trỏ
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                ok = false;
                break;
            }
            left++;
            right--;
        }

        System.out.println("Kết quả: " + ok);
    }

    // ================= FR4: REVERSE WORDS =================
    // Đảo ngược thứ tự các từ trong chuỗi
    static void reverseWords() {
        sc.nextLine(); // Clear buffer
        System.out.print("Nhập chuỗi: ");
        String input = sc.nextLine();

        // Tách chuỗi theo một hoặc nhiều khoảng trắng
        String[] words = input.trim().split("\\s+");

        // Dùng StringBuilder để nối chuỗi hiệu quả hơn
        StringBuilder sb = new StringBuilder();

        // Duyệt mảng từ cuối về đầu
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) sb.append(" ");
        }

        System.out.println("Chuỗi sau khi đảo:");
        System.out.println(sb);
    }

    // ================= FR5: HAPPY NUMBER =================
    // Kiểm tra số hạnh phúc bằng cách phát hiện vòng lặp
    static void happyNumber() {
        System.out.print("Nhập số n: ");
        int n = sc.nextInt();

        // Mảng lưu các giá trị đã xuất hiện
        int[] seen = new int[100];
        int size = 0;

        while (n != 1) {
            // Nếu n đã xuất hiện trước đó → vòng lặp
            if (exists(seen, size, n)) {
                System.out.println(" Không phải số hạnh phúc");
                return;
            }

            seen[size++] = n;
            n = sumSquareDigits(n);
        }

        System.out.println(" Là số hạnh phúc");
    }

    // Kiểm tra giá trị value có tồn tại trong mảng hay không
    static boolean exists(int[] arr, int size, int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) return true;
        }
        return false;
    }

    // Tính tổng bình phương các chữ số của n
    static int sumSquareDigits(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }
}
