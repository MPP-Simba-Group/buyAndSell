package com.mpp.buyAndSell.core.fp;

import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.comment.service.CommentService;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class FuctionalServiceClass {
    /*The first functionality that will implemented is most month that user participated
    Participation starts with posting,commenting, liking ...
    */


    //Finding the number of posts, comments, and likes for each month will give us the number of participation.
    //======================================================================
    //the number of posts
    static List<Item> itemsposts=new ItemService().findAll();
    static List<Comment> commentList= new CommentService().getAllcomments();//new ArrayList<>();
    //the following function creates a map that has lists of items or posts for each month.
    static Function<List<Item>, Map<Integer,List<Item>>> posts=(i)->i.stream()
            .collect(Collectors.groupingBy(p->p.getCreatedTime()
                    .toLocalDateTime()
                    .getMonthValue()));//month values are from 0 to 11.

    //The following funtions creates a Map of number items posted for each month.
    // months being the keys of the Map a
    //the number of items posted

    static Function<List<Item>,Map<Integer,Integer>> nppm =(i)->posts.apply(i).entrySet()
            .stream()
            .collect(Collectors.toMap(map-> map.getKey().intValue(), map->(map.getValue().size())));

    //The following code the number of

    static Function<List<Comment>, Map<Integer,List<Comment>>> comments=(i)->i.stream()
          .collect(Collectors.groupingBy(p->p.getCreatedDate()
                  .toLocalDateTime()
                  .getMonthValue()));//month values are from 0 to 11.

    static Function<List<Comment>,Map<Integer,Integer>> ncpm =(i)->comments.apply(i)
            .entrySet()
            .stream()
            .collect(Collectors.toMap(map-> map.getKey().intValue(), map->(map.getValue().size())));
    //=============================================================================
    //This is to find total number of likes for each month.
    static Function<List<Item>,Map<Integer,Integer>> nlpm =(i)->posts.apply(i)
            .entrySet()
            .stream()
            .collect(Collectors.toMap(map-> map.getKey().intValue(), map->(map.getValue().stream().mapToInt(y->y.getLikes()).sum())));

            //total

    /*
    * The following is for the third question
    *
    * Get product that exceeds k in price.
    *
    *
    *
    * */

    static BiFunction<List<Item>, Double, List<Item>> itemPosts=(i,k)->i.stream()
            .collect(Collectors.groupingBy(Item::getPrice))
            .entrySet().stream()
            .filter(d-> d.getKey() >k)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            .values().stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    //Most liked post item per month, the fourth question.

    static Function<List<Item>,Map<Integer,Optional<Item>>> mlItempm=(i)->posts.apply(i)
            .entrySet()
            .stream()
            .collect(Collectors.toMap(map-> ((Integer) map.getKey()), map->(map.getValue().stream()
                    .max((a, b) -> b.getLikes() - a.getLikes()))));

    //Implementation methods
    //most month that user participated
    //Participation starts with posting,commenting, liking ..
    public static int theMostActiveMonth(){ // o-Jan, 1-Feb, 2-March, ....., 11- Dec
        Map<Integer,Integer> temp1=new HashMap<>(nppm.apply(itemsposts));
        Map<Integer,Integer> temp2=new HashMap<>(nlpm.apply(itemsposts));
        Map<Integer,Integer> temp3= new HashMap<>(ncpm.apply(commentList));
        Map<Integer,Integer> temp4=Stream.concat(temp1.entrySet().stream(),temp2.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (value1,value2)->(value1.intValue()+value2.intValue())
                ));
        Map<Integer,Integer> resultFinal=Stream.concat(temp3.entrySet().stream(),temp4.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (value1,value2)->(value1.intValue()+value2.intValue())
                ));
        return Collections.max(resultFinal.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();

    }
    //Get products whose price exceeds k for instance $570.00
    public static List<Item> ItemsWithGreaterthanKprice(Double k){
        return itemPosts.apply(itemsposts,k);
    }

    //the most liked item for every month
    //the following implementation returns Map whose keys are months (0,1,...,11) and values are the most
    //liked items for that month.
    public static Map<Integer,Optional<Item>> theMostLikedItemsForEachMonth(){
        return mlItempm.apply(itemsposts);
    }
}
