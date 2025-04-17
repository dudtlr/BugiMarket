package capstone.market.repository;

import capstone.market.domain.*;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.profile_dto.SearchFilterDto;
import com.querydsl.core.BooleanBuilder;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;



@RequiredArgsConstructor

@Transactional(readOnly = true)
@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;


            @Override
            public List<PostDetailDto> searchFilterWithPaging(SearchFilterDto searchFilterDto) {

                QPost post = QPost.post;

                BooleanBuilder whereBuilder = new BooleanBuilder();
                Pageable pageable = PageRequest.of(searchFilterDto.getPage(), searchFilterDto.getSize());



                if (searchFilterDto.getTrack() != null && !searchFilterDto.getTrack().toString().isEmpty()) {
                    whereBuilder.and(post.track.eq(searchFilterDto.getTrack()));
                }

                if (searchFilterDto.getCollegeType() != null && !searchFilterDto.getCollegeType().toString().isEmpty()) {
                    whereBuilder.and(post.college.eq(searchFilterDto.getCollegeType()));

                }


                if (searchFilterDto.getCategories() != null && !searchFilterDto.getCategories().isEmpty()) {
                    BooleanExpression[] categoryExpressions = searchFilterDto.getCategories().stream()
                            .map(post.categoryType::eq)
                            .toArray(BooleanExpression[]::new);
                    whereBuilder.andAnyOf(categoryExpressions);
                }

                if (searchFilterDto.getViews() != null) {
                    whereBuilder.and(post.views.goe(searchFilterDto.getViews()));
                }
                if (searchFilterDto.getLikes() != null) {
                    whereBuilder.and(post.likes.goe(searchFilterDto.getLikes()));
                }




                //1. %검색어% => 인덱스 불가 => full scan => 성능 한계
//
//                if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
//                    whereBuilder.and(post.post_title.contains(searchFilterDto.getKeyword())
//                            .or(post.post_text.contains(searchFilterDto.getKeyword())));
//                }

                if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
                    whereBuilder.and(post.post_title.contains(searchFilterDto.getKeyword()));
                }

                //2. 검색어% => 인덱스 사용 가능 but 기능 한계
                // if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
                //                    whereBuilder.and(post.post_title.startsWith(searchFilterDto.getKeyword())
                //                            .or(post.post_text.startsWith(searchFilterDto.getKeyword())));
                //                }


                //3. mysql - full text search

//                if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
//
//                    NumberTemplate booleanTemplate= Expressions.numberTemplate(Double.class,
//                            "function('match',{0},{1})",post.post_title,searchFilterDto.getKeyword());
//
//                   whereBuilder.and(booleanTemplate.gt(0));
//                }




                if (searchFilterDto.getLocations() != null && !searchFilterDto.getLocations().isEmpty()) {
                    BooleanExpression[] categoryExpressions2 = searchFilterDto.getLocations().stream()
                            .map(post.locationType::eq)
                            .toArray(BooleanExpression[]::new);
                    whereBuilder.andAnyOf(categoryExpressions2);
                }



                if (searchFilterDto.getDepartments() != null && !searchFilterDto.getDepartments().isEmpty()) {
                    BooleanExpression[] categoryExpressions3 = searchFilterDto.getDepartments().stream()
                            .map(post.departmentType::eq)
                            .toArray(BooleanExpression[]::new);
                    whereBuilder.andAnyOf(categoryExpressions3);
                }



                    //BooleanBuilder finalWhere = whereBuilder.and(post.purchased.isNull());
                     //whereBuilder.and(post.purchased.isNull());

                    whereBuilder.and(post.status.eq(StatusType.판매중));

                    OrderSpecifier<?> order;
                    if (searchFilterDto.getSort() != null && searchFilterDto.getSort().equalsIgnoreCase("views")) {
                        order = post.views.desc();
                    } else if (searchFilterDto.getSort() != null && searchFilterDto.getSort().equalsIgnoreCase("likes")) {
                        order = post.likes.desc();
                    } else {
                        order = post.createdDate.desc();
                    }





                    // 페이징 처리
                    //long page_number = pageable.getOffset();

                    long page_number = searchFilterDto.getPage();
                    int page_size = pageable.getPageSize();

                    List<Post> filteredPosts = queryFactory
                            .selectFrom(post)
                            //.where(finalWhere)  // 판매되지 않은 게시글 필터링
                            .where(whereBuilder)
                            .orderBy(order)
                            .offset(page_number * page_size)
                            .limit(page_size)  // size 만큼의 데이터를 가져옴
                            .fetch();

                    List<PostDetailDto> SearchPosts = filteredPosts.stream()
                            .map(p -> new PostDetailDto(p))
                            .collect(Collectors.toList());



                    return SearchPosts;

                }





        }
