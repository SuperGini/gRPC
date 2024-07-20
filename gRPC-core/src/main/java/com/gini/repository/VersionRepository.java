package com.gini.repository;

import com.gini.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version, String> {
}
