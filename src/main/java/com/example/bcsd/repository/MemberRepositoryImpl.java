package com.example.bcsd.repository;

public class MemberRepositoryImpl implements MemberRepository {
    /*
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Member> memberRowMapper = (rs, rowNum) ->
            new Member(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
            );

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", memberRowMapper);
    }

    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper, id);
        return result.stream().findFirst();
    }

    public Optional<Member> findByEmail(String email) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE email = ?", memberRowMapper, email);
        return result.stream().findFirst();
    }

    public Member save(Member member) {
        jdbcTemplate.update("INSERT INTO member (id, name, email, password) VALUES (?, ?, ?, ?)",
                member.getId(), member.getName(), member.getEmail(), member.getPassword());
        return member;
    }

    public void update(Member member, Long id) {
        String sql = "UPDATE member SET name = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                member.getName(),
                member.getEmail(),
                member.getPassword(),
                id
        );
    }

    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM member WHERE id = ?", id) > 0;
    }
     */
}
