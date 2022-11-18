package com.example.contact.entity;


import lombok.*;
import org.hibernate.annotations.BatchSize;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CONTACT_TBL")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTACT_ID")
    private Long contactId;

    @Column(name = "CONTACT_NAME", nullable = false, unique = true)
    @NotBlank(message = "Error : The field Name must not be blank")
    @NotNull(message = "Error : The field Name must not be blank")
    private String contactName;

    @Column(name = "PHONE", unique = true)
    @Pattern(regexp = "[0-9]*" , message = ("Error : The field Phone , HomePhone , WorkPhone just enter numbers"))
    private String phone;

    @Column(name = "HOME_PHONE", unique = true)
    @Pattern(regexp = "[0-9]*" , message = ("Error : The field Phone , HomePhone , WorkPhone just enter numbers"))
    private String homePhone;

    @Column(name = "WORK_PHONE", unique = true)
    @Pattern(regexp = "[0-9]*" , message = ("Error : The field Phone , HomePhone , WorkPhone just enter numbers"))
    private String workPhone;

    @Column(name = "TAG_NAME")
    @Pattern(regexp = "#[0-9a-zA-Z]*.*" , message = ("Error : Tags"))
    private String tags;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "CONTACT_BLOCK", nullable = false)
    private boolean isBlock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;

}
