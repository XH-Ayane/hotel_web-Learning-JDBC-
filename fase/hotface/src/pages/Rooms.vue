<template>
  <el-card class="rooms-container">
    <template #header>
      <div class="header">
  <el-button type="primary" icon="Plus" @click="addRoom">新增客房</el-button>
  <el-button type="danger" icon="Delete" @click="deleteSelectedRooms" :disabled="selectedRooms.length === 0" style="margin-left: 10px;">删除选中的客房</el-button>
  <div class="search-filter" style="margin-left: auto; display: flex; gap: 10px; align-items: center;">
    <el-input v-model="searchKeyword" placeholder="搜索客房号" style="width: 200px;" @keyup.enter="handleSearch">
      <template #append>
        <el-icon @click="handleSearch"><Search /></el-icon>
      </template>
    </el-input>
    <el-select v-model="selectedTypeId" placeholder="客房类型" style="width: 150px;" @change="handleSearch">
      <el-option value="" label="全部类型"></el-option>
      <el-option v-for="type in roomTypes" :key="type.typeId" :label="type.typeName" :value="type.typeId"></el-option>
    </el-select>
    <el-select v-model="selectedStatus" placeholder="客房状态" style="width: 150px;" @change="handleSearch">
      <el-option value="" label="全部状态"></el-option>
      <el-option value="available" label="可预订"></el-option>
      <el-option value="occupied" label="已入住"></el-option>
      <el-option value="maintenance" label="维护中"></el-option>
    </el-select>
    <el-button @click="resetFilters">重置</el-button>
  </div>
</div>
    </template>
    <el-table border height="450" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="35"></el-table-column>
      <el-table-column prop="roomId" label="客房编号" width="100"></el-table-column>
      <el-table-column prop="roomNumber" label="客房号"></el-table-column>
      <!-- 正确的客房类型列 -->
      <el-table-column label="客房类型">
        <template #default="scope">
          <!-- 这个部分搞了个映射，原理是将typeId映射到对应的类型名称 -->
          <!-- 这样使用类型表就可以将typeId映射到对应的类型名称，避免直接从数据库中传值 -->
          {{ typeMap[scope.row.typeId] || '未知类型' }}
        </template>
      </el-table-column>
      <el-table-column prop="floor" label="楼层"></el-table-column>
      <el-table-column label="景观类型">
        <template #default="scope">
          {{ viewTypeMap[scope.row.viewType] || '未知景观' }}
        </template>
      </el-table-column>
      <el-table-column label="客房图片" width="150px">
        <template #default="scope">
          <img style="width: 100px; height: 100px;" :src='scope.row.roomImages ? "/api/" + scope.row.roomImages : "/default-room.jpg"' alt="客房图片">
        </template>
      </el-table-column>
      <el-table-column label="客房状态">
        <template #default="scope">
          <span style="color: green;" v-if="scope.row.status === 'available'">可预订</span>
          <span style="color: orange;" v-if="scope.row.status === 'occupied'">已入住</span>
          <span style="color: red;" v-if="scope.row.status === 'maintenance'">维护中</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <a style="cursor: pointer; margin-right: 10px" @click="updateRoom(scope.row)">修改</a>
          <a style="cursor: pointer; margin-right: 10px; color: red" @click="deleteRoom(scope.row.roomId)">删除</a>
          <el-dropdown>
            <span class="el-dropdown-link" style="border: 1px solid #dcdfe6; padding: 4px 12px; border-radius: 4px; cursor: pointer;">
  客房状态修改 <el-icon class="el-icon-arrow-down"></el-icon>
</span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item v-if="scope.row.status === 'available'" @click="updateRoomStatus(scope.row.roomId, 'occupied')">设为已入住</el-dropdown-item>
                <el-dropdown-item v-else-if="scope.row.status === 'occupied'" @click="updateRoomStatus(scope.row.roomId, 'available')">设为可预订</el-dropdown-item>
                <el-dropdown-item @click="updateRoomStatus(scope.row.roomId, 'maintenance')">设为维护中</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="prev, pager, next" :total="total" :page-size="pageSize" :current-page="currentPage" @current-change="handleCurrentChange" style="margin-top: 20px; text-align: center;"></el-pagination>
  </el-card>

  <el-dialog :title="titleMap[dialogTitle]" v-model="dialogVisible">
    <el-form :model="roomForm" :rules="rules" ref="roomRef" label-width="100px" class="roomForm">
      <el-form-item required label="客房类型">
        <el-select v-model="roomForm.typeId" placeholder="请选择客房类型">
          <el-option v-for="type in roomTypes" :key="type.typeId" :label="type.typeName" :value="type.typeId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客房号" prop="roomNumber">
        <el-input v-model="roomForm.roomNumber" placeholder="请输入客房号"></el-input>
      </el-form-item>
      <el-form-item label="楼层" prop="floor">
        <el-input type="number" v-model="roomForm.floor" placeholder="请输入楼层"></el-input>
      </el-form-item>
      <el-form-item label="景观类型" prop="viewType">
        <el-select v-model="roomForm.viewType" placeholder="请选择景观类型">
          <el-option label="城市景观" value="city"></el-option>
          <el-option label="园林景观" value="garden"></el-option>
          <el-option label="海景景观" value="ocean"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客房状态" prop="status">
        <el-select v-model="roomForm.status" placeholder="请选择客房状态">
          <el-option label="可预订" value="available"></el-option>
          <el-option label="已入住" value="occupied"></el-option>
          <el-option label="维护中" value="maintenance"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客房图片">
        <el-upload action="/api/upload" list-type="picture-card" :on-success="handleImageUpload">
          <el-icon><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item>
  <el-button type="danger" @click="deleteRoom(roomForm.roomId)">删除该房间</el-button>
  <el-button @click="handleCancel">取消</el-button>
  <el-button type="primary" @click="handleSubmit">确定</el-button>
</el-form-item>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { ElMessage, ElMessageBox, ElDropdown, ElDropdownMenu, ElDropdownItem, ElIcon, ElInput, ElSelect, ElOption } from 'element-plus';
import { Search } from '@element-plus/icons-vue';
import axiosInstance from '../axios/axiosInstance';

const dialogVisible = ref(false);
const dialogTitle = ref('addRoom');
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(0);
const tableData = ref([]);
const selectedRooms = ref([]);
const roomTypes = ref([]);
const searchKeyword = ref('');
const selectedTypeId = ref('');
const selectedStatus = ref('');
const typeMap = ref({}); // 添加类型映射表
const viewTypeMap = ref({
  'city': '城市景观',
  'garden': '园景',
  'lake': '海景'
}); // 添加景观类型映射表
const roomForm = ref({
  roomId: null,
  roomNumber: '',
  typeId: '',
  floor: '',
  viewType: '',
  status: 'available',
  roomImages: ''
});
const rules = ref({
  roomNumber: [{ required: true, message: '请输入客房号', trigger: 'blur' }],
  typeId: [{ required: true, message: '请选择客房类型', trigger: 'change' }],
  floor: [{ required: true, message: '请输入楼层', trigger: 'blur' }],
  viewType: [{ required: true, message: '请选择景观类型', trigger: 'change' }],
  status: [{ required: true, message: '请选择客房状态', trigger: 'change' }]
});
const titleMap = {
  addRoom: '新增客房',
  updateRoom: '修改客房'
};

onMounted(() => {
  loadRoomList();
  loadRoomTypes();
});

const loadRoomList = async () => {
  try {
    const response = await axiosInstance.get('/RoomServlet', {
      params: {
        method: 'getRoomsByPage',
        currentPage: currentPage.value,
        pageSize: pageSize.value,
        keyword: searchKeyword.value,
        typeId: selectedTypeId.value,
        status: selectedStatus.value
      }
    });
    if (response.data.message === 'success') {
      tableData.value = response.data.data.list;
      total.value = response.data.data.total;
    }
  } catch (error) {
    ElMessage.error('加载客房列表失败: ' + error.message);
  }
};

const loadRoomTypes = async () => {
  try {
    const response = await axiosInstance.get('/roomTypeServlet', {
      params: { method: 'getAllRoomTypes' }
    });
    if (response.data.message === 'success') {
      roomTypes.value = response.data.data;
      // 构建类型映射表
      roomTypes.value.forEach(type => {
        typeMap.value[type.typeId] = type.typeName;
      });
    }
  } catch (error) {
    ElMessage.error('加载客房类型失败: ' + error.message);
  }
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  loadRoomList();
};

const addRoom = () => {
  dialogTitle.value = 'addRoom';
  roomForm.value = { status: 'available' };
  dialogVisible.value = true;
};

const updateRoom = (row) => {
  dialogTitle.value = 'updateRoom';
  roomForm.value = { ...row };
  dialogVisible.value = true;
};

const handleSubmit = async () => {
  try {
    const method = dialogTitle.value === 'addRoom' ? 'saveRoom' : 'editRoom';
    const response = await axiosInstance.post('/RoomServlet', null, {
      params: { method, ...roomForm.value }
    });
    if (response.data.message === 'success') {
      ElMessage.success(dialogTitle.value === 'addRoom' ? '新增成功' : '修改成功');
      dialogVisible.value = false;
      loadRoomList();
    }
  } catch (error) {
    ElMessage.error((dialogTitle.value === 'addRoom' ? '新增' : '修改') + '失败: ' + error.message);
  }
};

const handleCancel = () => {
  dialogVisible.value = false;
};

const handleSearch = () => {
  currentPage.value = 1;
  loadRoomList();
};

const resetFilters = () => {
  searchKeyword.value = '';
  selectedTypeId.value = '';
  selectedStatus.value = '';
  currentPage.value = 1;
  loadRoomList();
};

const handleSelectionChange = (selection) => {
  selectedRooms.value = selection;
};

const deleteSelectedRooms = async () => {
  if (selectedRooms.value.length === 0) {
    ElMessage.warning('请先选择要删除的客房');
    return;
  }

  try {
    await ElMessageBox.confirm('确认要删除选中的客房吗？', '删除确认', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    });

    const roomIds = selectedRooms.value.map(room => room.roomId);
    const response = await axiosInstance.post('/RoomServlet', null, {
      params: {
        method: 'deleteRooms',
        roomIds: roomIds.join(',')
      }
    });

    if (response.data.message === 'success') {
      ElMessage.success('删除成功');
      selectedRooms.value = [];
      loadRoomList();
    }
  } catch (error) {
    if (error.name !== 'Cancel') {
      ElMessage.error('删除失败: ' + error.message);
    }
  }
};

const deleteRoom = async (roomId) => {
  try {
    await ElMessageBox.confirm('确认要删除该房间吗？', '删除确认', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    });

    const response = await axiosInstance.post('/RoomServlet', null, {
      params: {
        method: 'deleteRoom',
        roomId
      }
    });

    if (response.data.message === 'success') {
      ElMessage.success('删除成功');
      dialogVisible.value = false;
      loadRoomList();
    }
  } catch (error) {
    if (error.name !== 'Cancel') {
      ElMessage.error('删除失败: ' + error.message);
    }
  }
};

const updateRoomStatus = async (roomId, status) => {
  try {
    const response = await axiosInstance.post('/RoomServlet', null, {
      params: {
        method: 'updateRoomStatus',
        roomId,
        status
      }
    });
    if (response.data.message === 'success') {
      ElMessage.success('状态更新成功');
      loadRoomList();
    }
  } catch (error) {
    ElMessage.error('状态更新失败: ' + error.message);
  }
};

const handleImageUpload = (response, file, fileList) => {
  if (response.code === 200) {
    roomForm.value.roomImages = response.data;
    ElMessage.success('图片上传成功');
  } else {
    ElMessage.error('图片上传失败');
  }
};
</script>

<style scoped>
.rooms-container {
  padding: 20px;
}
.roomForm {
  padding: 20px;
}
.header {
  display: flex;
  align-items: center;
}
</style>


