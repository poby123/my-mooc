import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';
@Entity()
export class Organization {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "varchar", length: 50 })
    title!: string;
}