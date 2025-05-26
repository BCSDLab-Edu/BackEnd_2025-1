package com.example.bcsd.exception;

import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.bcsd.article.dto.CreateArticleRequest;
import com.example.bcsd.article.dto.UpdateArticleRequest;
import com.example.bcsd.article.service.ArticleService;
import com.example.bcsd.board.repository.BoardRepository;
import com.example.bcsd.member.dto.UpdateMemberRequest;
import com.example.bcsd.member.model.Member;
import com.example.bcsd.member.repository.MemberRepository;

@Aspect
@Component
public class ValidateDto {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ArticleService articleService;

    public ValidateDto(MemberRepository memberRepository, BoardRepository boardRepository, ArticleService articleService) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.articleService = articleService;
    }

    @Around("execution(* com.example.bcsd.article.service.ArticleService.CreateArticle(..))")
    public Object ValidateCreateArticle(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();
        CreateArticleRequest createArticleRequest = (CreateArticleRequest) args[0];

        if (!memberRepository.memberExists(createArticleRequest.authorId())) {
            throw new BadRequestException("Member does not exists");
        }

        if (!boardRepository.boardExists(createArticleRequest.boardId())) {
            throw new BadRequestException("Board does not exists");
        }

        return joinPoint.proceed();
    }

    @Around("execution(* com.example.bcsd.article.service.ArticleService.UpdateArticle(..))")
    public Object ValidateUpdateArticle(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();
        UpdateArticleRequest updateArticleRequest = (UpdateArticleRequest) args[1];

        if (updateArticleRequest.boardId() != null) { return joinPoint.proceed(); }

        if (!boardRepository.boardExists(updateArticleRequest.boardId())) {
            throw new BadRequestException("Board does not exists");
        }

        return joinPoint.proceed();
    }

    @Around("execution(* com.example.bcsd.member.service.MemberService.UpdateMember(..))")
    public Object ValidateUpdateMember(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();
        Long updatingMemberId = (Long) args[0];
        UpdateMemberRequest updateMemberRequest = (UpdateMemberRequest) args[1];

        if (updateMemberRequest.email() == null) { return joinPoint.proceed(); }

        Optional<Member> member = memberRepository.findByEmail(updateMemberRequest.email());

        if (member.isPresent() && !member.get().getId().equals(updatingMemberId)) {
            throw new ConflictException("Email conflicts");
        }

        return joinPoint.proceed();
    }

    @Around("execution(* com.example.bcsd.member.service.MemberService.DeleteMember(..))")
    public Object ValidateDeleteMember(ProceedingJoinPoint joinPoint) throws Throwable {
        Long memberId = (Long) joinPoint.getArgs()[0];

        if (articleService.GetArticlesByMemberId(memberId).articleCount() != 0) {
            throw new BadRequestException("Written articles are exists");
        }

        return joinPoint.proceed();
    }

    @Around("execution(* com.example.bcsd.board.service.BoardService.DeleteBoard(..))")
    public Object ValidateDeleteBoard(ProceedingJoinPoint joinPoint) throws Throwable {
        Long boardId = (Long) joinPoint.getArgs()[0];

        if (articleService.GetArticlesByBoardId(boardId).articleCount() != 0) {
            throw new BadRequestException("Written articles are exists");
        }

        return joinPoint.proceed();
    }
}