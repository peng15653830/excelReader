/**
 * The MIT License
 * <p/>
 * Copyright (c) 2012 www.myjeeva.com
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.myjeeva.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myjeeva.poi.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.junit.Test;

/**
 * Demonstration of Generic Excel File (XLSX) Reading using Apache POI
 *
 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam M.</a>
 */
public class ExcelWorkSheetHandlerTest {
    private static final Log LOG = LogFactory.getLog(ExcelWorkSheetHandlerTest.class);

    /**
     * @throws Exception
     */
    @Test
    public void testEmployee() throws Exception {
        String EMPLOYEE_DATA_FILE_PATH = "src/test/resources/fgsyg.xlsx";
        String COMPANY_DATA_FILE_PATH = "src/test/resources/fgs.xlsx";
        // Input File initialize
        File employeeFile = new File(EMPLOYEE_DATA_FILE_PATH);
        File companyFile = new File(COMPANY_DATA_FILE_PATH);
        InputStream employeeInputStream = new FileInputStream(employeeFile);
        InputStream companyInputStream = new FileInputStream(companyFile);

        Map<String, String> employeeMapping = new HashMap<String, String>();
        employeeMapping.put("HEADER", "fgsname,name,passwd,email,phone,regionid,address,createtime");
        employeeMapping.put("A", "fgsname");
        employeeMapping.put("B", "name");
        employeeMapping.put("C", "passwd");
        employeeMapping.put("D", "email");
        employeeMapping.put("E", "phone");
        employeeMapping.put("F", "regionid");
        employeeMapping.put("G", "address");
        employeeMapping.put("H", "createtime");

        Map<String, String> companyMapping = new HashMap<String, String>();
        companyMapping.put("HEADER", "id,name");
        companyMapping.put("A", "id");
        companyMapping.put("B", "name");

        // The package open is instantaneous, as it should be.
        OPCPackage employeepkg = null;
        OPCPackage companypkg = null;
        try {

            ExcelWorkSheetHandler<EmployeeVO> employeeWorkSheetHandler =
                    new ExcelWorkSheetHandler<EmployeeVO>(EmployeeVO.class, employeeMapping);

            ExcelWorkSheetHandler<CompanyVO> companyWorkSheetHandler =
                    new ExcelWorkSheetHandler<CompanyVO>(CompanyVO.class, companyMapping);

            employeepkg = OPCPackage.open(employeeInputStream);
            companypkg = OPCPackage.open(companyInputStream);

            ExcelSheetCallback sheetCallback = new ExcelSheetCallback() {
                private int sheetNumber = 0;

                @Override
                public void startSheet(int sheetNum, String sheetName) {
                    this.sheetNumber = sheetNum;
                    System.out.println("Started processing sheet number=" + sheetNumber
                            + " and Sheet Name is '" + sheetName + "'");
                }

                @Override
                public void endSheet() {
                    System.out.println("Processing completed for sheet number=" + sheetNumber);
                }
            };

            System.out.println("Constructor: pkg, employeeWorkSheetHandler, sheetCallback");
            ExcelReader employeeExcelReader = new ExcelReader(employeepkg, employeeWorkSheetHandler, sheetCallback);
            ExcelReader companyExcelReader = new ExcelReader(companypkg, companyWorkSheetHandler, sheetCallback);
            employeeExcelReader.process();
            companyExcelReader.process();

            if (employeeWorkSheetHandler.getValueList().isEmpty() || companyWorkSheetHandler.getValueList().isEmpty()) {
                // No data present
                LOG.error("sHandler.getValueList() is empty");
            } else {

                LOG.info(employeeWorkSheetHandler.getValueList().size()
                        + " no. of records read from given excel worksheet successfully.");
                LOG.info(companyWorkSheetHandler.getValueList().size()
                        + " no. of records read from given excel worksheet successfully.");

                displayEmployeeList(employeeWorkSheetHandler.getValueList(), companyWorkSheetHandler.getValueList());
            }


        } catch (RuntimeException are) {
            LOG.error(are.getMessage(), are.getCause());
        } catch (InvalidFormatException ife) {
            LOG.error(ife.getMessage(), ife.getCause());
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe.getCause());
        } finally {
            IOUtils.closeQuietly(employeeInputStream);
            IOUtils.closeQuietly(companyInputStream);
            try {
                if (null != employeepkg) {
                    employeepkg.close();
                }
                if (null != companypkg) {
                    companypkg.close();
                }
            } catch (IOException e) {
                // just ignore IO exception
            }
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void testMember() throws Exception {
        //String MEMBER_DATA_FILE_PATH = "src/test/resources/huiyuan.xlsx";
        String MEMBER_DATA_FILE_PATH = "src/test/resources/member6898.xlsx";
        String EMPLOYEEP_DATA_FILE_PATH = "src/test/resources/fgsygsc.xlsx";
        String REGION_DATA_FILE_PATH = "src/test/resources/region.xlsx";
        // Input File initialize
        File memberFile = new File(MEMBER_DATA_FILE_PATH);
        File employeeFile = new File(EMPLOYEEP_DATA_FILE_PATH);
        File regionFile = new File(REGION_DATA_FILE_PATH);
        InputStream memberInputStream = new FileInputStream(memberFile);
        InputStream employeeInputStream = new FileInputStream(employeeFile);
        InputStream regionInputStream = new FileInputStream(regionFile);

        Map<String, String> memberMapping = new HashMap<String, String>();

        //memberMapping.put("HEADER", "name,fgsname,fgsygname,mobile,email,loginname,loginpassword,realname,address,regionid,businesslicense,regtime");
        memberMapping.put("HEADER", "id,regionid");
        memberMapping.put("A", "id");
        memberMapping.put("B", "regionid");
//        memberMapping.put("C", "fgsygname");
//        memberMapping.put("D", "mobile");
//        memberMapping.put("E", "email");
//        memberMapping.put("F", "loginname");
//        memberMapping.put("G", "loginpassword");
//        memberMapping.put("H", "realname");
//        memberMapping.put("I", "address");
//        memberMapping.put("J", "regionid");
//        memberMapping.put("K", "businesslicense");
//        memberMapping.put("L", "regtime");

        Map<String, String> employeeMapping = new HashMap<String, String>();

        employeeMapping.put("HEADER", "id,name");
        employeeMapping.put("A", "id");
        employeeMapping.put("B", "name");


        Map<String, String> regionMapping = new HashMap<String, String>();

        regionMapping.put("HEADER", "regionId,regionName,parentRegionId,parentRegionName");
        regionMapping.put("A", "regionId");
        regionMapping.put("B", "parentRegionId");
        regionMapping.put("C", "regionName");
        regionMapping.put("D", "parentRegionName");

        // The package open is instantaneous, as it should be.
        OPCPackage memberpkg = null;
        OPCPackage employeepkg = null;
        OPCPackage regionpkg = null;
        try {

            ExcelWorkSheetHandler<MemberVO> memberWorkSheetHandler =
                    new ExcelWorkSheetHandler<MemberVO>(MemberVO.class, memberMapping);
            ExcelWorkSheetHandler<EmployeePVO> employeeWorkSheetHandler =
                    new ExcelWorkSheetHandler<EmployeePVO>(EmployeePVO.class, employeeMapping);
            ExcelWorkSheetHandler<RegionVO> regionWorkSheetHandler =
                    new ExcelWorkSheetHandler<RegionVO>(RegionVO.class, regionMapping);

            memberpkg = OPCPackage.open(memberInputStream);
            employeepkg = OPCPackage.open(employeeInputStream);
            regionpkg = OPCPackage.open(regionInputStream);

            ExcelSheetCallback sheetCallback = new ExcelSheetCallback() {
                private int sheetNumber = 0;

                @Override
                public void startSheet(int sheetNum, String sheetName) {
                    this.sheetNumber = sheetNum;
                    System.out.println("Started processing sheet number=" + sheetNumber
                            + " and Sheet Name is '" + sheetName + "'");
                }

                @Override
                public void endSheet() {
                    System.out.println("Processing completed for sheet number=" + sheetNumber);
                }
            };

            System.out.println("Constructor: pkg, employeeWorkSheetHandler, sheetCallback");
            ExcelReader memberExcelReader = new ExcelReader(memberpkg, memberWorkSheetHandler, sheetCallback);
            ExcelReader employeeExcelReader = new ExcelReader(employeepkg, employeeWorkSheetHandler, sheetCallback);
            ExcelReader regionExcelReader = new ExcelReader(regionpkg, regionWorkSheetHandler, sheetCallback);

            memberExcelReader.process();
            employeeExcelReader.process();
            regionExcelReader.process();

            if (memberWorkSheetHandler.getValueList().isEmpty() || employeeWorkSheetHandler.getValueList().isEmpty()) {
                // No data present
                LOG.error("sHandler.getValueList() is empty");
            } else {

                LOG.info(memberWorkSheetHandler.getValueList().size()
                        + " no. of records read from given excel worksheet successfully.");


                //displayMemberList(memberWorkSheetHandler.getValueList(), employeeWorkSheetHandler.getValueList());

                displayRegionList(memberWorkSheetHandler.getValueList(), regionWorkSheetHandler.getValueList());

            }



        } catch (RuntimeException are) {
            LOG.error(are.getMessage(), are.getCause());
        } catch (InvalidFormatException ife) {
            LOG.error(ife.getMessage(), ife.getCause());
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe.getCause());
        } finally {
            IOUtils.closeQuietly(memberInputStream);
            try {
                if (null != memberpkg) {
                    memberpkg.close();
                }
                if (null != employeepkg) {
                    employeepkg.close();
                }

            } catch (IOException e) {
                // just ignore IO exception
            }
        }
    }


    private void displayRegionList(List<MemberVO> memberValueList, List<RegionVO> regionValueList) {
        StringBuilder memberInfoSb = new StringBuilder();
        StringBuilder regionSb = new StringBuilder();
        int memberId=1;
        for(MemberVO mvo : memberValueList){
            String regionId=mvo.getRegionid();
            String regionName="";
            String parentRegionName="";
            for(RegionVO regionVO:regionValueList){
                if(regionId.equals(regionVO.getRegionId())){
                    regionName=regionVO.getRegionName();
                    parentRegionName=regionVO.getParentRegionName();
                }
            }

            regionSb.append("UPDATE cs_member_info, (SELECT Id,ParentId FROM cs_region WHERE NAME = '"+regionName+"' AND LEVEL = 3 ) AS thirdlevel,  ( SELECT Id FROM cs_region WHERE NAME = '"+parentRegionName+"' AND LEVEL = 2 ) AS secondlevel SET cs_member_info.regionId = thirdlevel.Id WHERE cs_member_info.memberId ="+mvo.getId()+" AND thirdlevel.ParentId = secondlevel.Id;");
            regionSb.append("\n");

            memberId+=1;
        }
        Path regionFile = Paths.get("cs_memberinfo_update_region_1.sql");
        writeToFile(regionFile, regionSb.toString());
    }



        private void displayMemberList(List<MemberVO> memberValueList, List<EmployeePVO> employeeValueList) {
        StringBuilder memberSb = new StringBuilder();
        StringBuilder memberInfoSb = new StringBuilder();
        StringBuilder memberAccountSb = new StringBuilder();
        int count = 0;
        int memberid = 1;
        for (MemberVO mvo : memberValueList) {
            int branchCrewId = 0;
            for (EmployeePVO evo : employeeValueList) {
                if (evo.getName().equals(mvo.getFgsygname())) {
                    branchCrewId = Integer.parseInt(evo.getId());
                    break;
                }
            }
            if (branchCrewId == 0) {
                LOG.error("empty branchCrew:" + (++count) + mvo.getName());
                continue;
            }

            memberSb.append("INSERT INTO `cs_member` (`id`, `loginname`, `email`, `mobile`, `loginpassword`, `regtime`, `branchcrewid`, `memberLevelId`, `regSource`, `regLanguageType`, `mailValidated`, `status`, `loginPasswordStrength`, `entry`, `auditstate`, `c4MemberCardNoValidated`, `mobileValidated`, `payPasswordStrength`) VALUES(" +
                    memberid + "," + "'" + mvo.getLoginname() + "','" + mvo.getEmail() + "','" + mvo.getMobile() + "','" + mvo.getLoginpassword() + "','" + convertDate(mvo.getRegtime()) + "'," + branchCrewId + ","+1+ ","+1+ ","+1+ ","+0+ ","+1+ ","+1+ ","+2+ ","+2+ ",'"+ "','"+ "','"+"');");
            memberSb.append("\n");


            memberInfoSb.append("INSERT INTO `cs_member_info` (`id`, `memberid`,`realname`, `regionid` ,`address`, `businesslicense`, `gender`) VALUES("
                    +memberid+","+ memberid + "," + "'" + mvo.getRealname() + "'," + mvo.getRegionid() + ",'" + mvo.getAddress() + "','" + mvo.getBusinesslicense() +"',"+0 +");");
            memberInfoSb.append("\n");


            memberAccountSb.append("INSERT INTO `cs_member_account` (`id`, `memberid`,`pointAccountBalance`, `expAccountBalance`) VALUES("
                    +memberid+","+ memberid + "," + 0 + "," + 0 + ");");
            memberAccountSb.append("\n");

            memberid = memberid + 1;
        }

        Path memberFile = Paths.get("cs_member.sql");
        writeToFile(memberFile, memberSb.toString());
        Path memberInfoFile = Paths.get("cs_member_info.sql");
        writeToFile(memberInfoFile, memberInfoSb.toString());
        Path memberAccountFile = Paths.get("cs_member_account.sql");
        writeToFile(memberAccountFile, memberAccountSb.toString());

    }


    private static void displayEmployeeList(List<EmployeeVO> evos, List<CompanyVO> cvos) {

        StringBuilder sb = new StringBuilder();
        for (EmployeeVO evo : evos) {
            String branchId = "";
            for (CompanyVO cvo : cvos) {
                if (evo.getFgsname().equals(cvo.getName())) {
                    branchId = cvo.getId();
                    break;
                }
            }
            if (StringUtils.isEmpty(branchId)) {
                LOG.error("empty branchId");
            }
            sb.append("INSERT INTO `cs_branch_crew` ( `name`, `branchid` `phone`, `email`, `regionId`, `address`,  `createTime`) VALUES("
                    + "'" + evo.getName() + "'," + branchId + ",'" + evo.getPhone() + "','" + evo.getEmail() + "'," + evo.getRegionid() + ",'" + evo.getAddress() + "','" + evo.getCreatetime() + "')");
            sb.append("\n");
        }
        System.out.println(sb.toString());
        Path file = Paths.get("cs_branch_crew.sql");
        writeToFile(file, sb.toString());

    }

    private static void writeToFile(Path file, String content) {
        try {
            Files.write(file, content.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertDate(String datestr) {
        SimpleDateFormat sdfsrc = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date datesrc = null;
        try {
            datesrc = sdfsrc.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdfdes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdfdes.format(datesrc);
        return result;
    }
}
