package calories_control.features.imc.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImcJPA extends JpaRepository<ImcModel, Long> {
    @Query(value = """
                SELECT
                imc.id AS imc_id,
                imc.altura,
                imc.peso,
                imc.resultado,
                imc.clasificacion,
                u.id AS user_id,
                u.name AS user_name,
                u.email AS user_email
                FROM imc
                JOIN users u ON imc.user_id = u.id
                WHERE imc.id =:imcId
            """, nativeQuery = true)
    IMCWithUserProjection findIMCById(@Param("imcId") Long imcId);

    @Query(value = """
                SELECT
                imc.id AS imc_id,
                imc.altura,
                imc.peso,
                imc.resultado,
                imc.clasificacion,
                u.id AS user_id,
                u.name AS user_name,
                u.email AS user_email
                FROM imc
                JOIN users u ON imc.user_id = u.id
            """, nativeQuery = true)
    List<IMCWithUserProjection> findIMCAll();

    @Query(value = """
            SELECT
            imc.id AS imc_id,
            imc.altura,
            imc.peso,
            imc.resultado,
            imc.clasificacion,
            u.id AS user_id,
            u.name AS user_name
            FROM imc
            JOIN users u ON imc.user_id = u.id
            WHERE u.id =:userId

                """, nativeQuery = true)
    Optional<List<IMCWithUserProjection>> findIMCByUserId(@Param("userId") Long userId);

}
