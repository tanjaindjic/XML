package XmlWeb.repository;

import XmlWeb.model.security.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
