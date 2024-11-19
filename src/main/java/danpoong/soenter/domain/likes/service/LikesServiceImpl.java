package danpoong.soenter.domain.likes.service;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.repository.EnterpriseRepository;
import danpoong.soenter.domain.likes.converter.LikesConverter;
import danpoong.soenter.domain.likes.dto.LikesDTO.LikesResponse.GetLikeResponse;
import danpoong.soenter.domain.likes.entity.Likes;
import danpoong.soenter.domain.likes.repository.LikesRepository;
import danpoong.soenter.domain.user.entity.User;
import danpoong.soenter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;

    @Transactional
    public void addLike(String userId, Long enterpriseId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기업입니다."));

        if (likesRepository.existsByUserAndEnterprise(user, enterprise)) {
            throw new RuntimeException("이미 즐겨찾기에 추가된 기업입니다.");
        }

        Likes like = Likes.builder()
                .user(user)
                .enterprise(enterprise)
                .build();

        likesRepository.save(like);
    }

    @Transactional(readOnly = true)
    public List<GetLikeResponse> getLikes(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        List<Likes> likes = likesRepository.findByUser(user);

        return likes.stream()
                .map(like -> LikesConverter.toGetLikeResponse(like.getEnterprise()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeLike(String userId, Long enterpriseId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기업입니다."));

        likesRepository.deleteByUserAndEnterprise(user, enterprise);
    }
}