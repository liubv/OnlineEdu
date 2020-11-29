package com.lbvguli.educms.service;

import com.lbvguli.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-28
 */
public interface CrmBannerService extends IService<CrmBanner> {
    List<CrmBanner> selectAllBanner();
}
