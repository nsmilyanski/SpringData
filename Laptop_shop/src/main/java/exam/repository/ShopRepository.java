package exam.repository;

import exam.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    boolean existsShopByName(String name);

    Shop findShopByName(String name);
}
