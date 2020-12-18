package com.iot.repository;

import com.iot.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Integer> {
}
