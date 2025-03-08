package com.business.coffeshop.mapper;

import com.business.coffeshop.constant.CommonStatusEnum;
import com.business.coffeshop.constant.RoleCodeEnum;
import com.business.coffeshop.dto.AccountDto;
import com.business.coffeshop.entity.Account;
import com.business.coffeshop.helper.StringHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    @Mappings({
            @Mapping(source = "account.deleted", target = "status", qualifiedByName = "getAccountStatus"),
            @Mapping(source = "account.roleCode", target = "roleCode", qualifiedByName = "getRoleCode")
    })
    public abstract AccountDto toAccountDto(Account account);

    @Named("getAccountStatus")
    public String getAccountStatus(Boolean deleted) {
        return Boolean.TRUE.equals(deleted) ? CommonStatusEnum.ACT.getDescription() : CommonStatusEnum.IACT.getDescription();
    }

    @Named("getRoleCode")
    public String getRoleCode(String roleCode) {
        return RoleCodeEnum.getRoleNameByCode(roleCode);
    }

}


