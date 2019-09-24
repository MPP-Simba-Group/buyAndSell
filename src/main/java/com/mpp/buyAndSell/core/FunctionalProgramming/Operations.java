package com.mpp.buyAndSell.core.FunctionalProgramming;

import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.item.entity.IowaCitiesEnum;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.entity.ItemCategoryEnum;
import com.mpp.buyAndSell.core.user.entity.User;

import org.joda.time.Period;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class Operations {

	// used in item service
	public Function<List<Item>, IowaCitiesEnum> getMostCityHasProducts = (L) -> L.stream()
			.collect(Collectors.groupingBy(Item::getCity)).entrySet().stream()
			.sorted((city1, city2) -> city2.getValue().size() - city1.getValue().size()).findFirst().get().getKey();

//used in item service
	/*
	 * public TriFunction<List<Item>, Long,LocalDate,
	 * List<Item>>getProductsWithKdays=(L,K,M)->L.stream()
	 * .filter(e->(Timestamp.valueOf( LocalDateTime.now()) -
	 * e.getCreatedTime())==K).collect(Collectors.toList());
	 */
	public BiFunction<List<Item>, Timestamp, List<Item>> getProductsAfterDate = (l, t) -> l.stream()
			.filter(i -> i.getCreatedTime().after(t)).collect(Collectors.toList());

	public Function<List<Item>, User> getMostedActiveSeller = (L) -> L.stream()
			.collect(Collectors.groupingBy(Item::getUser)).entrySet().stream()
			.sorted((user1, user2) -> user2.getValue().size() - user1.getValue().size()).findFirst().get().getKey();

    //used in user service
    public Function<List<User>,List<User>> blockedUsers = (l) -> l.stream()
            .filter(user -> user.isBlocked().equals(true))
            .collect(Collectors.toList());

    //used in item service
    public Function<List<Item>, ItemCategoryEnum> topCategory = (Items)-> Items.stream()
            .collect(Collectors.groupingBy(Item::getCategory))
            .entrySet().stream()
            .max((i1,i2)-> i2.getValue().size() - i1.getValue().size()).get().getKey();

    //used in user service
    public BiFunction<List<Comment>,Integer,List<Comment>> userComments = (c,u)-> c.stream()
            .filter(x -> x.getUser().getId() == u)
            .collect(Collectors.toList());

    //used in user service
    public BiFunction<List<Item>,Integer,List<Item>> userItems = (I,U)-> I.stream()
            .filter(x -> x.getUser().getId() == U)
            .collect(Collectors.toList());

    //used in user service
    public Function<List<Item>,List<User>> top5Sellers = (i)-> i.stream()
            .collect(Collectors.groupingBy(Item::getUser))
            .entrySet().stream()
            .sorted((i1,i2)-> i2.getValue().size() - i1.getValue().size())
            .limit(5)
            .map(list -> list.getKey())
            .collect(Collectors.toList());

    //used in user service
    public Function<List<Comment>,List<User>> top5Commentors = (i)-> i.stream()
            .collect(Collectors.groupingBy(Comment::getUser))
            .entrySet().stream()
            .sorted((i1,i2)-> i2.getValue().size() - i1.getValue().size())
            .limit(5)
            .map(list -> list.getKey())
            .collect(Collectors.toList());

    //used in item service
    public Function<List<Item>, Optional<Double>> totalItemPriceInQuarter = l -> l.stream()
            .filter(item -> item.getCreatedTime().after(Timestamp.valueOf(LocalDateTime.now().plusMonths(-3))))
            .map(item -> item.getPrice())
            .reduce((p1, p2) -> p1+p2);

    //used in item service
    public Function<List<Item>, Optional<Double>> totalItemPriceInYear = l -> l.stream()
            .filter(item -> item.getCreatedTime().after(Timestamp.valueOf(LocalDateTime.now().plusYears(-1))))
            .map(item -> item.getPrice())
            .reduce((p1, p2) -> p1+p2);

    //used in item service
    public Function<List<Item>,List<Item>> topActiveItems = l -> l.stream()
            .sorted((l1,l2)-> l2.getLikes()+l2.getComments().size() - (l1.getLikes()+l1.getComments().size()))
            .limit(5)
            .collect(Collectors.toList());

	public Function<List<Comment>, User> getMostedActiveCommenter = (L) -> L.stream()
			.collect(Collectors.groupingBy(Comment::getUser)).entrySet().stream()
			.sorted((user1, user2) -> user2.getValue().size() - user1.getValue().size()).findFirst().get().getKey();
}
