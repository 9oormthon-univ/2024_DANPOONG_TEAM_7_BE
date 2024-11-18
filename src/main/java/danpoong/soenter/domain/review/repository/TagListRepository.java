package danpoong.soenter.domain.review.repository;

import danpoong.soenter.domain.review.entity.Review;
import danpoong.soenter.domain.review.entity.TagList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagListRepository extends JpaRepository<TagList, Long> {
    List<TagList> findByReview(Review review);
}