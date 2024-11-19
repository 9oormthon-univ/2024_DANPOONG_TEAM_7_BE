package danpoong.soenter.domain.likes.service;

import danpoong.soenter.domain.likes.dto.LikesDTO.LikesResponse.GetLikeResponse;

import java.util.List;

public interface LikesService {
    void addLike(String userId, Long enterpriseId);
    List<GetLikeResponse> getLikes(String userId);
    void removeLike(String userId, Long enterpriseId);
}
