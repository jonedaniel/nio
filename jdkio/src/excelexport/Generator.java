/*
package excelexport;

import excelexport.model.Model;

public class Generator {
    public static void main(String[] args) {
        ExcelExport.exportPoi(sql, Model.class);
    }

    static final String sql = "select ID id,RESBLOCK_NAME resblockName,RESBLOCK_CODE resblockCode from (select RESBLOCK_NAME,RESBLOCK_CODE,ID from hdic.T_HM_RESBLOCK) where ROWNUM < 10";
    static final String sql2 = "with\n" +
            "     t as (select * from commonframework.tc_data_dict_main),\n" +
            "     f as (select f.resblock_id, f.file_type, f.label, count(1) count  from hdic.t_hm_resblock_file f group by f.resblock_id, f.file_type, f.label),\n" +
            "     s as (select  house.resblock_id, count(1) count, sum(case HOUSE_USE when '117500000003' then 1 else 0 end) write_count,  sum(case HOUSE_USE when '117500000002' then 1 else 0 end) comm_count, sum(case HOUSE_USE when '117500000001' then 1 else 0 end) liv_count from  hdic.t_hm_house house  inner join hdic.t_hm_building_const cons on cons.building_id=house.building_id where instr (house.building_name, '车位') = 0 and house.status = '1' group by house.resblock_id)\n" +
            "select\n" +
            "       a.resblock_name 项目名称\n" +
            "    , replace(replace(a.belong_type,'1','一手'),'2','二手') 权属类型\n" +
            "    , decode(a.is_agent,'102300000001','是', '102300000002','否') 代理\n" +
            "    , decode(a.is_target,'102300000001','是', '102300000002','否') 目标盘\n" +
            "    , decode(a.is_blame,'102300000001','是', '102300000002','否') 责任盘\n" +
            "    , a.district_name 城区\n" +
            "    , a.bizcircle_name 商圈\n" +
            "    , case when a.map_x is null then '' else  a.map_x||','||a.map_y end 坐标\n" +
            "    , a.resblock_alias 楼盘别名\n" +
            "    , (select wm_concat(t.dict_name) from  t where instr(a.resblock_type||',', t.dict_code||',')>0 ) 房屋用途\n" +
            "    , (select t.dict_name from t where t.dict_code=a.resblock_location)  环线\n" +
            "    , a.admin_addr  行政地址\n" +
            "    , a.project_location 项目区位\n" +
            "    , a.warrant_addr 权证地址\n" +
            "    , a.building_count 楼栋总数\n" +
            "    , s.write_count 写字楼数\n" +
            "    , a.house_count 总户数\n" +
            "    , s.count  配置户数\n" +
            "    , s.comm_count  商业户数\n" +
            "    , s.liv_count  住宅户数\n" +
            "    , (select t.resblock_name from hdic.t_hm_resblock t where t.id =  a.community_one) 一级社区\n" +
            "    , (select t.resblock_name from hdic.t_hm_resblock t where t.id =  a.community_two)  二级社区\n" +
            "    , a.entry_count 入口数量\n" +
            "    , trim(regexp_replace(replace(replace(replace(' 东:'||a.entry_east||' 南:'|| a.entry_south||' 西:'||a.entry_west||' 北:'||a.entry_north||' ','206200000001','行人'),'206200000002','行车'),'206200000003','不限'),'.:[ ]+')) 入口通行情况\n" +
            "    , (select wm_concat(t.dict_name) from  t where instr(a.resblock_mating||',', t.dict_code||',')>0 ) 周边配套\n" +
            "    , (select wm_concat(t.dict_name) from t where instr(a.resblock_label||',', t.dict_code||',')>0 ) 楼盘标签\n" +
            "    , b.build_year 建筑年代\n" +
            "    , b.get_area_time 拿地时间\n" +
            "    , b.build_area 建筑面积\n" +
            "    , b.build_space 占地面积\n" +
            "    , (select t.dict_name  from  t where t.dict_code=b.is_split) 人车分流\n" +
            "    , b.green_rate 绿化率\n" +
            "    , b.volume_rate 容积率\n" +
            "    , b.car_rate 车位比\n" +
            "    , (select t.dict_name from  t where t.dict_code = b.build_style) 建筑风格\n" +
            "    , b.loft_height 抬地高度\n" +
            "    , b.car_down_count 地下车位数\n" +
            "    , b.car_up_count 地上车位数\n" +
            "    , nullif(nvl(b.car_up_count,0) + nvl(b.car_down_count,0),0) 车位总数\n" +
            "    , (select  greatest ( max(nvl(d.spacing_east,0)), max(nvl(d.spacing_west,0)), max(nvl(d.spacing_south,0)),  max(nvl(d.spacing_north,0)) ) || '-' || least (  max(nvl(d.spacing_east,0)),  max(nvl(d.spacing_west,0)), max(nvl(d.spacing_south,0)), max(nvl(d.spacing_north,0)))  from hdic.t_hm_building_const d where  d.resblock_id = a. id) 楼间距\n" +
            "    , b.floor_height 层高\n" +
            "    , (select wm_concat(distinct t.dict_name) from hdic.t_hm_building_const bc join t on t.dict_code=bc.facade_Material and t.parent_code='2022' where bc.resblock_id=a.id ) 立面材料\n" +
            "    , (select wm_concat(nvl(d.developer_name,de.developer_name)) from hdic.t_hm_resblock_developer d left join hdic.t_hm_developer de on de.pkid=d.developer_id where d.resblock_id=a.id and (d.developer_type='200100000001' or d.developer_type is null)) 开发商名称\n" +
            "    , (select wm_concat(nvl(d.developer_name,de.developer_name)) from hdic.t_hm_resblock_developer d left join hdic.t_hm_developer de on de.pkid=d.developer_id where d.resblock_id=a.id and d.developer_type='200100000002') 建筑设计方\n" +
            "    , (select wm_concat(nvl(d.developer_name,de.developer_name)) from hdic.t_hm_resblock_developer d left join hdic.t_hm_developer de on de.pkid=d.developer_id where d.resblock_id=a.id and d.developer_type='200100000003') 园林设计方\n" +
            "    , (select wm_concat(p.property_name) from hdic.t_hm_resblock_property d join hdic.t_hm_property p on d.property_id=p.pkid where d.resblock_id=a.id) 物业公司名称\n" +
            "    , (select wm_concat(d.property_fee) from hdic.t_hm_resblock_property d where d.resblock_id=a.id) 物业费\n" +
            "    , (select wm_concat( s.school_name) from hdic.t_hm_school s join hdic.t_hm_resblock_school r on r.school_id=s.pkid where r.ration_id=a.id) 学校\n" +
            "    , (select wm_concat(e.club_name) from hdic.t_hm_resblock_club d join hdic.t_hm_club e on d.club_id=e.pkid where d.resblock_id=a.id) 会所\n" +
            "    , f.cold_water_fee 冷水费\n" +
            "    , f.hot_warter_fee 热水费\n" +
            "    , f.electric_fee 电费\n" +
            "    , f.gas_fee  燃气费\n" +
            "    , f.club_fee 会所费\n" +
            "    , f.heating_fee  采暖费\n" +
            "    , f.property_fee 物业费\n" +
            "    , f.satellite_fee  卫星费\n" +
            "    , f.car_manger_fee 车位管理费\n" +
            "    , decode(f.car_rant_fee_start,f.car_rant_fee_end,f.car_rant_fee_start||'',REGEXP_REPLACE(f.car_rant_fee_start||'-'||f.car_rant_fee_end,'^-|-$','')) 车位租金\n" +
            "    , decode(f.car_space_amount_start,f.car_space_amount_end,f.car_space_amount_start||'',REGEXP_REPLACE(f.car_space_amount_start||'-'||f.car_space_amount_end,'^-|-$','')）  车位售价\n" +
            "    , c.sale_location 地理位置\n" +
            "    , c.sale_transport 交通优势\n" +
            "    , c.sale_suport 配套优势\n" +
            "    , c.sale_green 园林绿化\n" +
            "    , c.sale_build_style 景观亮点\n" +
            "    , c.sale_special_value 特有价值\n" +
            "    , c.sale_up_potential 升值潜力\n" +
            "    , c.sale_weak_point 不足\n" +
            "    , (select wm_concat(distinct t.dict_name) from hdic.t_hm_structure_rule s join t on s.build_type=t.dict_code where s.resblock_id=a.id) 产品类型\n" +
            "    , (select wm_concat(distinct t.dict_name)  from hdic.t_hm_building_const d left join t on t.dict_code=d.const_type  where d.resblock_id=a.id) 建筑类型\n" +
            "    , case when a.building_file is null then 0 else 1 end 座栋分布图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label is not null) 实景图相册\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label='206600000001') 楼栋外观图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label='206600000002') 园林景观\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label='206600000003') 社区基础设施图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label='206600000004') 社区正门\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label='206600000005') 车库\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label='206600000006') 会所图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='1' and f.label='206600000007') 物业图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label is not null) 周边配套图相册\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000001') 商超\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000007') 餐饮\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000008') 学校\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000002') 医院\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000005') 公园\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000009') 银行\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000004') 娱乐设施\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000003') 交通\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000006') 药店\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='5' and f.label='206000000010') 其他\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='2') 位置图相册\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label is not null) 效果图相册\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label='206600000001') 楼栋外观图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label='206600000002') 园林景观\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label='206600000003') 社区基础设施图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label='206600000004') 社区正门\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label='206600000005') 车库\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label='206600000006') 会所图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='3' and f.label='206600000007') 物业图\n" +
            "    , (select sum(f.count) from f where f.resblock_id=a.id and f.file_type='4') 样板间相册\n" +
            "    , (case when a.cover_file is not null then 1  end) 封面图相册\n" +
            "    , (select sum(case when f.file_type in ('2','4') or f.label is not null then f.count else 0 end )+(case when a.cover_file is not null then 1 else 0 end)  from f where f.resblock_id=a.id and f.file_type in('1','2','3','4','5')) 图片总计\n" +
            "\n" +
            "    from hdic.t_hm_resblock a\n" +
            "    left join hdic.t_hm_resblock_build b on a.id=b.resblock_id\n" +
            "    left join hdic.t_hm_resblock_sale_point c on a.id= c.resblock_id\n" +
            "    left join s on s.resblock_id=a.id\n" +
            "    left join hdic.t_hm_resblock_fee f on f.resblock_id=a.id\n" +
            "    where a.city_id=110000";
}
*/
