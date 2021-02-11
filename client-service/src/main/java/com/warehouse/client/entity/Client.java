package com.warehouse.client.entity;

import com.warehouse.client.constraint.PhoneNumberFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @NotBlank(message = "{com.warehouse.client.entity.Client.name.NotBlank}")
    private String name;

    @NotBlank(message = "{com.warehouse.client.entity.Client.lastName.NotBlank}")
    private String lastName;

    @NotBlank(message = "{com.warehouse.client.entity.Client.address.NotBlank}")
    private String address;

    @PhoneNumberFormatter
    private String phoneNumber;
}
