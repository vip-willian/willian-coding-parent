package cn.willian.coding.thread;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-10 11:09:00
 */
@Slf4j
@SuppressWarnings("all")

/**
 * -------------------------
 * 丨   runAsync    丨           丨
 * -------------------------
 */
public class CompletableFutureDemo {

    private BookSevice bookSevice = new BookSevice();
    private ReportSevice reportSevice = new ReportSevice();
    private CommentsSevice commentsSevice = new CommentsSevice();

    public static void main(String[] args) throws Exception {

        CompletableFutureDemo demo = new CompletableFutureDemo();
        long start = System.currentTimeMillis();
        List<Book> bookes1 = demo.getBooksSync();
        System.out.println("执行结果: " + bookes1);
        System.out.println("执行耗时: " + ((System.currentTimeMillis() - start) / 1000) + "s");

        start = System.currentTimeMillis();
        System.out.println("------------------------------------");

        List<Book> bookes2 = demo.getBooksAync();
        System.out.println("执行结果: " + bookes2);
        System.out.println("执行耗时: " + ((System.currentTimeMillis() - start) / 1000) + "s");
    }

    public List<Book> getBooksSync() {

        List<Book> books = bookSevice.findBooks();

        List<Integer> bookIds = books.stream().map(Book::getId).collect(Collectors.toList());

        Map<Integer, Repository> reps = reportSevice.findReps(bookIds);

        Map<Integer, Comments> comments = commentsSevice.findComments(bookIds);

        for (Book book : books) {
            book.setRepository(reps.get(book.getId()));
            book.setComments(comments.get(book.getId()));
        }
        return books;
    }

    public List<Book> getBooksAync() throws Exception {

        List<Book> books = Lists.newArrayList();
        CompletableFuture<List<Book>> bookBaseTask = CompletableFuture.supplyAsync(() -> bookSevice.findBooks()).whenComplete((res, ex) -> {
            books.addAll(res);
        });
        //
        CompletableFuture<Void> repoTask = bookBaseTask.thenAcceptAsync(bookes -> {
            List<Integer> bookIds = bookes.stream().map(Book::getId).collect(Collectors.toList());
            Map<Integer, Repository> reps = reportSevice.findReps(bookIds);
            for (Book book : bookes) {
                book.setRepository(reps.get(book.getId()));
            }
        });
        CompletableFuture<Void> commentTask = bookBaseTask.thenAcceptAsync(bookes -> {
            List<Integer> bookIds = bookes.stream().map(Book::getId).collect(Collectors.toList());
            Map<Integer, Comments> comments = commentsSevice.findComments(bookIds);
            for (Book book : bookes) {
                book.setComments(comments.get(book.getId()));
            }
        });
        CompletableFuture.allOf(bookBaseTask, repoTask, commentTask).get();
        return books;
    }


    @Data
    public static class Book {

        private Integer id;
        private String name;
        private Integer price;
        private Repository repository;
        private Comments comments;

        public Book(Integer id, String name, Integer price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    @Data
    public static class Repository {

        private Integer bookId;
        private Integer count;

        public Repository(Integer bookId, Integer count) {
            this.bookId = bookId;
            this.count = count;
        }
    }

    @Data
    public static class Comments {

        private Integer bookId;
        private String content;

        public Comments(Integer bookId, String content) {
            this.bookId = bookId;
            this.content = content;
        }
    }

    public class BookSevice {

        public List<Book> findBooks() {

            Book book1 = new Book(1, "duboo", 20);
            Book book2 = new Book(2, "mysql", 50);
            Book book3 = new Book(3, "spring", 80);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Lists.newArrayList(book1, book2, book3);
        }
    }

    public class ReportSevice {

        public Map<Integer, Repository> findReps(List<Integer> bookIds) {

            Repository rep1 = new Repository(1, 40);
            Repository rep2 = new Repository(2, 50);
            Repository rep3 = new Repository(3, 80);

            HashMap<Integer, Repository> map = Maps.newHashMap();
            map.put(1, rep1);
            map.put(2, rep2);
            map.put(3, rep2);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return map;
        }
    }

    public class CommentsSevice {

        public Map<Integer, Comments> findComments(List<Integer> bookIds) {

            Comments comment1 = new Comments(1, "很不错");
            Comments comment2 = new Comments(2, "超级棒");
            Comments comment3 = new Comments(3, "一般般");

            HashMap<Integer, Comments> map = Maps.newHashMap();
            map.put(1, comment1);
            map.put(2, comment2);
            map.put(3, comment3);

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return map;
        }
    }
}
