package com.lbvguli.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbvguli.commonutils.R;
import com.lbvguli.educms.entity.CrmBanner;
import com.lbvguli.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台bannerApi
 * </p>
 *
 * @author testjava
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;


    //分页查询banner
    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,
                        @PathVariable long limit){
        Page<CrmBanner> pageParam = new Page<>(page, limit);
        bannerService.page(pageParam,null);

        return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());

    }

    @CacheEvict(value = "banner", allEntries=true)
    @PostMapping("addBanner")
    public R addBanner(@RequestBody(required = true) CrmBanner banner){
        bannerService.save(banner);
        return R.ok();
    }


    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }


    @ApiOperation(value = "修改Banner")
    @CacheEvict(value = "banner", allEntries=true)
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @CacheEvict(value = "banner", allEntries=true)
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }
}

